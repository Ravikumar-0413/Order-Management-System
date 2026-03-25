 package oms.order.Management.System.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import oms.order.Management.System.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // get items of a specific order
    List<OrderItem> findByOrderId(Long orderId);
}