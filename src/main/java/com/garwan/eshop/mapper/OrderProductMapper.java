package com.garwan.eshop.mapper;

import com.garwan.eshop.model.OrderProduct;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class OrderProductMapper {
    private OrderProductMapper() {
    }

    public static List<com.garwan.eshop.jpa.entity.OrderProduct> toOrderProducts(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
            .map(OrderProductMapper::toOrderProduct)
            .collect(toList());
    }

    public static com.garwan.eshop.jpa.entity.OrderProduct toOrderProduct(OrderProduct orderProduct) {
        return com.garwan.eshop.jpa.entity.OrderProduct.builder()
            .productId(orderProduct.getProductId())
            .count(orderProduct.getCount())
            .price(orderProduct.getPrice())
            .build();
    }

    public static List<OrderProduct> fromOrderProducts(List<com.garwan.eshop.jpa.entity.OrderProduct> orderProducts) {
        return orderProducts.stream()
            .map(OrderProductMapper::fromOrderProduct)
            .collect(toList());
    }

    private static OrderProduct fromOrderProduct(com.garwan.eshop.jpa.entity.OrderProduct orderProduct) {
        return OrderProduct.builder()
            .productId(orderProduct.getProductId())
            .count(orderProduct.getCount())
            .price(orderProduct.getPrice())
            .build();
    }
}
