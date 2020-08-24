package com.garwan.eshop.service;

import com.garwan.eshop.jpa.repository.OrderRepository;
import com.garwan.eshop.mapper.OrderMapper;
import com.garwan.eshop.model.CreateOrderRequest;
import com.garwan.eshop.model.Order;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(CreateOrderRequest createOrderRequest) {
        return OrderMapper.fromOrder(orderRepository.save(OrderMapper.toOrder(createOrderRequest)));
    }

    public List<Order> getAll(){
        return OrderMapper.fromOrders(orderRepository.findAll());
    }
}
