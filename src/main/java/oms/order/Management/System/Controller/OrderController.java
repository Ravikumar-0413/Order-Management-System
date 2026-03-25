 package oms.order.Management.System.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import oms.order.Management.System.Entity.OrderEntity;
import oms.order.Management.System.Entity.OrderItem;
import oms.order.Management.System.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
 
    @PostMapping("/place")
    public ResponseEntity<Map<String, Object>> placeOrder(
            @RequestParam Long customerId,
            @RequestBody List<OrderItem> items) {

        OrderEntity order = orderService.placeOrder(customerId, items);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order placed successfully");
//        response.put("order", order);

        return ResponseEntity.ok(response);
    }

     
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders() {

        List<OrderEntity> orders = orderService.getAllOrders();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Orders fetched successfully");
        response.put("data", orders);

        return ResponseEntity.ok(response);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOrderById(@PathVariable Long id) {

        OrderEntity order = orderService.getOrderById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order fetched successfully");
        response.put("data", order);

        return ResponseEntity.ok(response);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order deleted successfully");

        return ResponseEntity.ok(response);
    }
}