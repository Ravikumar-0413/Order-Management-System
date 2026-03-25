 package oms.order.Management.System.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import oms.order.Management.System.Entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // get orders by customer
    List<OrderEntity> findByCustomerId(Long customerId);

    // get orders by status
    List<OrderEntity> findByStatus(String status);
}