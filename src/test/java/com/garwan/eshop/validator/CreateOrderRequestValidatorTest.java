package com.garwan.eshop.validator;

import com.garwan.eshop.model.CreateOrderRequest;
import com.garwan.eshop.model.OrderProduct;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;

class CreateOrderRequestValidatorTest {
    private CreateOrderRequestValidator createOrderRequestValidator;

    @BeforeEach
    void setup() {
        createOrderRequestValidator = new CreateOrderRequestValidator();
    }

    @Test
    void shouldReturnTrueWhenTotalPriceIsEqualToTotalPriceOfAllItems() {
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
            .totalPrice(new BigDecimal("15").add(new BigDecimal("28")))
            .items(asList(orderProduct("5", 3), orderProduct("7", 4)))
            .build();

        boolean actualResult = createOrderRequestValidator.isValid(createOrderRequest, null);

        assertThat(actualResult).isTrue();
    }

    @Test
    void shouldReturnFalseWhenTotalPriceIsNotEqualToTotalPriceOfAllItems() {
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
            .totalPrice(new BigDecimal("15"))
            .items(asList(orderProduct("5", 3), orderProduct("7", 4)))
            .build();

        boolean actualResult = createOrderRequestValidator.isValid(createOrderRequest, null);

        assertThat(actualResult).isFalse();
    }

    @Test
    void shouldReturnTrueWhenTotalPriceIsNull() {
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
            .totalPrice(null)
            .items(asList(orderProduct("5", 3), orderProduct("7", 4)))
            .build();

        boolean actualResult = createOrderRequestValidator.isValid(createOrderRequest, null);

        assertThat(actualResult).isTrue();
    }

    @Test
    void shouldReturnTrueWhenItemsIsNull() {
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
            .totalPrice(new BigDecimal("2"))
            .items(null)
            .build();

        boolean actualResult = createOrderRequestValidator.isValid(createOrderRequest, null);

        assertThat(actualResult).isTrue();
    }

    @Test
    void shouldReturnTrueWhenCreateOrderRequestIsNull() {
        boolean actualResult = createOrderRequestValidator.isValid(null, null);

        assertThat(actualResult).isTrue();
    }

    private static OrderProduct orderProduct(String price, int count) {
        return OrderProduct.builder().count(count).price(new BigDecimal(price)).build();
    }
}