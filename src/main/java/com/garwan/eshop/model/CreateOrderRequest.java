package com.garwan.eshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateOrderRequest {
    @NotNull
    @PositiveOrZero
    private BigDecimal totalPrice;
    @Valid
    @NotEmpty
    private List<OrderProduct> items;
    @NotNull
    @PastOrPresent
    private LocalDateTime time;
}
