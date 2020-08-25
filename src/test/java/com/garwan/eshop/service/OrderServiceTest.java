package com.garwan.eshop.service;

import com.garwan.eshop.jpa.entity.OrderEntity;
import com.garwan.eshop.jpa.entity.OrderProductEntity;
import com.garwan.eshop.jpa.entity.UserEntity;
import com.garwan.eshop.jpa.repository.OrderRepository;
import com.garwan.eshop.jpa.repository.UserRepository;
import com.garwan.eshop.mapper.OrderMapper;
import com.garwan.eshop.model.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        orderService = new OrderService(orderRepository, userRepository);
    }

    @Test
    void shouldFindAllForUser() {
        Long userId = 1L;
        UserEntity userEntity = UserEntity.builder().id(userId).build();
        List<OrderEntity> expectedOrderEntities =
            asList(orderEntity(1L, userEntity), orderEntity(2L, userEntity));
        when(orderRepository.findAllByUserId(userId)).thenReturn(expectedOrderEntities);

        List<Order> actualResult = orderService.findAllForUser(userId);

        assertThat(actualResult).isEqualTo(OrderMapper.fromOrderEntities(expectedOrderEntities));
    }

    private static OrderEntity orderEntity(long id, UserEntity userEntity) {
        OrderEntity order = OrderEntity.builder()
            .id(id)
            .time(LocalDateTime.now().minusDays(id))
            .totalPrice(new BigDecimal(id + "32.12"))
            .user(userEntity)
            .build();
        order.addItems(asList(orderProductEntity(id + 1L, 1L), orderProductEntity(id + 1L, 2L)));
        return order;
    }

    private static OrderProductEntity orderProductEntity(long productId, long suffix) {
        return OrderProductEntity.builder()
            .count((int) (productId * suffix))
            .price(new BigDecimal(productId + suffix))
            .productId(productId)
            .order(null)
            .build();
    }
}