package com.garwan.eshop.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderProduct {
    @NotNull
    @Positive
    private Long productId;
    @NotNull
    @PositiveOrZero
    private BigDecimal price;
    @NotNull
    @Positive
    private Integer count;
}
