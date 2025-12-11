package br.com.diegoplaninscheck.n3_seguranca_backend.controller;

import br.com.diegoplaninscheck.n3_seguranca_backend.model.Order;
import br.com.diegoplaninscheck.n3_seguranca_backend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PreAuthorize("hasRole('USER_GET') || hasRole('admin')")
    @Operation(summary = "List all order")
    @GetMapping()
    public ResponseEntity<List<Order>> listAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @PreAuthorize("hasRole('USER_CREATE') || hasRole('admin')")
    @Operation(summary = "Create new Order")
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(201).body(orderService.create(order));
    }

    @PreAuthorize("hasRole('USER_DELETE') || hasRole('admin')")
    @Operation(summary = "Delete order")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Order>> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.status(204).body(orderService.findAll());
    }

    @PreAuthorize("hasRole('USER_UPDATE') || hasRole('admin')")
    @Operation(summary = "Update order")
    @PutMapping("/finishSale/{id}")
    public ResponseEntity<Order> finishSaleOrder(@PathVariable Long id, @RequestBody Order order) {
        try{
            return ResponseEntity.ok(orderService.update(id, order));
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
