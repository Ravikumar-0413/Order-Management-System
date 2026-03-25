 package oms.order.Management.System.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import oms.order.Management.System.Entity.Product;
import oms.order.Management.System.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

     
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

     
    public Product getProductById(Long id) {
    	return productRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found with ID: " + id
                ));
    }

    
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        existing.setStockQuantity(updatedProduct.getStockQuantity());

        return productRepo.save(existing);
    }

    
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepo.delete(product);
    }

    public void validateStock(Product product, int quantity) {

        if (quantity <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid quantity: " + quantity + ". Quantity must be greater than 0."
            );
        }

        if (product.getStockQuantity() < quantity) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insufficient stock for product '" + product.getName() +
                    "'. Available: " + product.getStockQuantity() +
                    ", Requested: " + quantity
            );
        }
    }
    

    
    public void reduceStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() - quantity);
    }

     
//    public void restoreStock(Product product, int quantity) {
//        product.setStockQuantity(product.getStockQuantity() + quantity);
//    }
}