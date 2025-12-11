package br.com.diegoplaninscheck.n3_seguranca_backend.service;

import br.com.diegoplaninscheck.n3_seguranca_backend.Repository.OrderRepository;
import br.com.diegoplaninscheck.n3_seguranca_backend.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService (OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order create (Order order) {
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void delete (Long id) {
        orderRepository.deleteById(id);
    }

    public Order update (Long id, Order newOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setCustomer(newOrder.getCustomer());
            order.setUser(newOrder.getUser());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found!"));
    }
}
