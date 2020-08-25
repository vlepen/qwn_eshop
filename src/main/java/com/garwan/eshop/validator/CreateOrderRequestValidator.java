package com.garwan.eshop.validator;

import com.garwan.eshop.model.CreateOrderRequest;
import com.garwan.eshop.model.OrderProduct;
import com.garwan.eshop.model.annotation.SumOfCreateOrdertItems;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for total price of order to be created. Total price must be equal to the total price of all items in order.
 * Validation is triggered only when all required attributes are non null, otherwise returns true.
 */
public class CreateOrderRequestValidator implements ConstraintValidator<SumOfCreateOrdertItems, CreateOrderRequest> {
    @Override
    public void initialize(SumOfCreateOrdertItems constraint) {
    }

    @Override
    public boolean isValid(CreateOrderRequest createOrderRequest, ConstraintValidatorContext context) {
        if (createOrderRequest == null || createOrderRequest.getItems() == null ||
            createOrderRequest.getTotalPrice() == null) {
            return true;
        }
        return createOrderRequest.getItems() != null &&
            createOrderRequest.getTotalPrice().equals(calculateItemsTotalPrice(createOrderRequest.getItems()));
    }

    private BigDecimal calculateItemsTotalPrice(List<OrderProduct> items) {
        return items.stream()
            .map(item -> item.getPrice().multiply(new BigDecimal(item.getCount())))
            .reduce(BigDecimal::add)
            .orElse(new BigDecimal("0"));
    }
}
