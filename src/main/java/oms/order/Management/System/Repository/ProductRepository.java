 package oms.order.Management.System.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import oms.order.Management.System.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // get available products
    List<Product> findByStockQuantityGreaterThan(int quantity);

    // search by name
    List<Product> findByNameContaining(String name);
}