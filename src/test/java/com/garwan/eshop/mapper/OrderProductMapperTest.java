package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.OrderProductEntity;
import com.garwan.eshop.model.OrderProduct;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;

class OrderProductMapperTest {
    @Test
    void shouldMapFromOrderProductEntities() {
        OrderProductEntity orderProduct1 = orderProductEntity(1L, 2L);
        OrderProductEntity orderProduct2 = orderProductEntity(2L, 3L);

        List<OrderProduct> actualResult =
            OrderProductMapper.fromOrderProductEntities(asList(orderProduct1, orderProduct2));

        assertThat(actualResult).containsExactly(
            OrderProductMapper.fromOrderProductEntity(orderProduct1),
            OrderProductMapper.fromOrderProductEntity(orderProduct2)
        )
            .inOrder();
    }

    @Test
    void shouldMapToOrderProductEntities() {
        OrderProduct orderProduct1 = orderProduct(1L);
        OrderProduct orderProduct2 = orderProduct(2L);

        List<OrderProductEntity> actualResult =
            OrderProductMapper.toOrderProductEntities(asList(orderProduct1, orderProduct2));

        assertThat(actualResult).containsExactly(
            OrderProductMapper.toOrderProductEntity(orderProduct1),
            OrderProductMapper.toOrderProductEntity(orderProduct2)
        )
            .inOrder();
    }

    @Test
    void shouldMapToOrderProductEntity() {
        OrderProduct orderProduct = orderProduct(1L);

        OrderProductEntity actualResult = OrderProductMapper.toOrderProductEntity(orderProduct);

        assertThat(actualResult).isEqualTo(
            OrderProductEntity.builder()
                .productId(orderProduct.getProductId())
                .count(orderProduct.getCount())
                .price(orderProduct.getPrice())
                .build()
        );
    }

    private static OrderProductEntity orderProductEntity(long productId, long suffix) {
        return OrderProductEntity.builder()
            .count((int) (productId * suffix))
            .price(new BigDecimal(productId + suffix))
            .productId(productId)
            .order(null)
            .build();
    }

    private static OrderProduct orderProduct(Long productId) {
        return OrderProduct.builder()
            .productId(productId)
            .price(new BigDecimal(productId + "4.3"))
            .count(productId.intValue() + 3)
            .build();
    }
}