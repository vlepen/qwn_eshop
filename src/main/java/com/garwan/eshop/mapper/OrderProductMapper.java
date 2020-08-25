package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.OrderProductEntity;
import com.garwan.eshop.model.OrderProduct;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class OrderProductMapper {
    private OrderProductMapper() {
    }

    public static List<OrderProductEntity> toOrderProductEntities(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
            .map(OrderProductMapper::toOrderProductEntity)
            .collect(toList());
    }

    public static OrderProductEntity toOrderProductEntity(OrderProduct orderProduct) {
        return OrderProductEntity.builder()
            .productId(orderProduct.getProductId())
            .count(orderProduct.getCount())
            .price(orderProduct.getPrice())
            .build();
    }

    public static List<OrderProduct> fromOrderProductEntities(List<OrderProductEntity> orderProducts) {
        return orderProducts.stream()
            .map(OrderProductMapper::fromOrderProductEntity)
            .collect(toList());
    }

    static OrderProduct fromOrderProductEntity(OrderProductEntity orderProduct) {
        return OrderProduct.builder()
            .productId(orderProduct.getProductId())
            .count(orderProduct.getCount())
            .price(orderProduct.getPrice())
            .build();
    }
}
