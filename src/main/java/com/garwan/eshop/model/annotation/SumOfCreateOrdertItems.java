package com.garwan.eshop.model.annotation;

import com.garwan.eshop.validator.CreateOrderRequestValidator;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CreateOrderRequestValidator.class)
public @interface SumOfCreateOrdertItems {
    String message() default "Total price of order must be equal to total price of all items";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
