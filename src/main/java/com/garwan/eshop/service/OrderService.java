package com.garwan.eshop.service;

import com.garwan.eshop.jpa.repository.OrderRepository;
import com.garwan.eshop.jpa.repository.UserRepository;
import com.garwan.eshop.mapper.OrderMapper;
import com.garwan.eshop.model.CreateOrderRequest;
import com.garwan.eshop.model.EshopUser;
import com.garwan.eshop.model.Order;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order create(CreateOrderRequest createOrderRequest, EshopUser eshopUser) {
        return OrderMapper.fromOrderEntity(orderRepository.save(OrderMapper.toOrderEntity(
            createOrderRequest,
            userRepository.findById(eshopUser.getId())
                .orElseThrow(() -> new RuntimeException("Current user is no longer present in repository"))
        )));
    }

    public List<Order> findAllForUser(Long userId) {
        return OrderMapper.fromOrderEntities(orderRepository.findAllByUserId(userId));
    }
}
