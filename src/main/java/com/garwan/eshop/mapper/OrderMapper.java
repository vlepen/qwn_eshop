package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.OrderEntity;
import com.garwan.eshop.jpa.entity.UserEntity;
import com.garwan.eshop.model.CreateOrderRequest;
import com.garwan.eshop.model.Order;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public final class OrderMapper {
    private OrderMapper() {
    }

    public static OrderEntity toOrderEntity(CreateOrderRequest createOrderRequest, UserEntity user) {
        OrderEntity order = OrderEntity.builder()
            .totalPrice(createOrderRequest.getTotalPrice())
            .time(createOrderRequest.getTime())
            .user(user)
            .build();
        order.addItems(OrderProductMapper.toOrderProductEntities(createOrderRequest.getItems()));
        return order;
    }

    public static Order fromOrderEntity(OrderEntity order) {
        return Order.builder()
            .id(order.getId())
            .time(order.getTime())
            .totalPrice(order.getTotalPrice())
            .items(OrderProductMapper.fromOrderProductEntities(order.getItems()))
            .build();
    }

    public static List<Order> fromOrderEntities(Iterable<OrderEntity> orders) {
        return StreamSupport.stream(orders.spliterator(), false)
            .map(OrderMapper::fromOrderEntity)
            .collect(toList());
    }
}
