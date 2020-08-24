package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.User;
import com.garwan.eshop.model.CreateOrderRequest;
import com.garwan.eshop.model.Order;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public final class OrderMapper {
    private OrderMapper() {
    }

    public static com.garwan.eshop.jpa.entity.Order toOrder(CreateOrderRequest createOrderRequest, User user) {
        com.garwan.eshop.jpa.entity.Order order = com.garwan.eshop.jpa.entity.Order.builder()
            .totalPrice(createOrderRequest.getTotalPrice())
            .time(createOrderRequest.getTime())
            .user(user)
            .build();
        order.addItems(OrderProductMapper.toOrderProducts(createOrderRequest.getItems()));
        return order;
    }

    public static Order fromOrder(com.garwan.eshop.jpa.entity.Order order) {
        return Order.builder()
            .id(order.getId())
            .time(order.getTime())
            .totalPrice(order.getTotalPrice())
            .items(OrderProductMapper.fromOrderProducts(order.getItems()))
            .build();
    }

    public static List<Order> fromOrders(Iterable<com.garwan.eshop.jpa.entity.Order> orders) {
        return StreamSupport.stream(orders.spliterator(), false)
            .map(OrderMapper::fromOrder)
            .collect(toList());
    }
}
