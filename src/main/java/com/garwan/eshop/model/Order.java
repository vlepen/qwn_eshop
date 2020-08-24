package com.garwan.eshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Order {
    private Long id;
    private BigDecimal totalPrice;
    private List<OrderProduct> items;
    private LocalDateTime time;
}
