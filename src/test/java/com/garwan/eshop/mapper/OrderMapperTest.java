package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.OrderEntity;
import com.garwan.eshop.jpa.entity.OrderProductEntity;
import com.garwan.eshop.jpa.entity.UserEntity;
import com.garwan.eshop.model.CreateOrderRequest;
import com.garwan.eshop.model.Order;
import com.garwan.eshop.model.OrderProduct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;

class OrderMapperTest {
    @Test
    void shouldMapFromOrderEntities() {
        OrderEntity orderEntity1 = orderEntity(1L);
        OrderEntity orderEntity2 = orderEntity(2L);

        List<Order> actualResult = OrderMapper.fromOrderEntities(asList(orderEntity1, orderEntity2));

        assertThat(actualResult).containsExactly(
            OrderMapper.fromOrderEntity(orderEntity1),
            OrderMapper.fromOrderEntity(orderEntity2)
        )
            .inOrder();
    }

    @Test
    void shouldMapFromOrderEntity() {
        OrderEntity orderEntity = orderEntity(1L);

        Order actualResult = OrderMapper.fromOrderEntity(orderEntity);

        assertThat(actualResult).isEqualTo(Order.builder()
            .id(orderEntity.getId())
            .time(orderEntity.getTime())
            .totalPrice(orderEntity.getTotalPrice())
            .items(OrderProductMapper.fromOrderProductEntities(orderEntity.getItems()))
            .build());
    }

    @Test
    void shouldMapToOrderEntity() {
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
            .totalPrice(new BigDecimal("23.3"))
            .time(LocalDateTime.now().minusDays(1L))
            .items(asList(orderProduct(1L), orderProduct(2L)))
            .build();
        UserEntity userEntity = userEntity();

        OrderEntity actualResult = OrderMapper.toOrderEntity(createOrderRequest, userEntity);

        OrderEntity expectedOrderEntity = OrderEntity.builder()
            .user(userEntity)
            .totalPrice(createOrderRequest.getTotalPrice())
            .time(createOrderRequest.getTime())
            .id(null)
            .build();
        expectedOrderEntity.addItems(OrderProductMapper.toOrderProductEntities(createOrderRequest.getItems()));
        assertThat(actualResult).isEqualTo(expectedOrderEntity);
    }

    private static OrderProduct orderProduct(Long productId) {
        return OrderProduct.builder()
            .productId(productId)
            .price(new BigDecimal(productId + "4.3"))
            .count(productId.intValue() + 3)
            .build();
    }

    private static OrderEntity orderEntity(long id) {
        return OrderEntity.builder()
            .id(id)
            .time(LocalDateTime.now().minusDays(1L))
            .totalPrice(new BigDecimal(id + "3.3"))
            .user(userEntity())
            .items(asList(orderProductEntity(1L, id), orderProductEntity(2L, id)))
            .build();
    }

    private static OrderProductEntity orderProductEntity(long productId, long suffix) {
        return OrderProductEntity.builder()
            .count((int) (productId * suffix))
            .price(new BigDecimal(productId + suffix))
            .productId(productId)
            .order(null)
            .build();
    }

    private static UserEntity userEntity() {
        return UserEntity.builder()
            .email("email")
            .id(2L)
            .password("password")
            .username("username")
            .build();
    }
}