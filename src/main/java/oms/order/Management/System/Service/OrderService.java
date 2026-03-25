 package oms.order.Management.System.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oms.order.Management.System.Entity.Customer;
import oms.order.Management.System.Entity.OrderEntity;
import oms.order.Management.System.Entity.OrderItem;
import oms.order.Management.System.Entity.Product;
import oms.order.Management.System.Repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

   
    public OrderEntity placeOrder(Long customerId, List<OrderItem> items) {

        if (items == null || items.isEmpty()) {
            throw new RuntimeException("Order must have at least one item");
        }

        Customer customer = customerService.getCustomerById(customerId);

        OrderEntity order = new OrderEntity();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (OrderItem item : items) {

            Long productId = item.getProduct().getId();
            Product product = productService.getProductById(productId);

            int quantity = item.getQuantity();

            productService.validateStock(product, quantity);

            item.setProduct(product);
            item.setPrice(product.getPrice());

            double subtotal = product.getPrice() * quantity;
            item.setSubtotal(subtotal);

            item.setOrder(order);

            productService.reduceStock(product, quantity);

            totalAmount += subtotal;
            orderItems.add(item);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepo.save(order);
    }

     
    public List<OrderEntity> getAllOrders() {
        return orderRepo.findAll();
    }

   
    public OrderEntity getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    
    public void deleteOrder(Long id) {
        OrderEntity order = getOrderById(id);
        orderRepo.delete(order);
    }
}