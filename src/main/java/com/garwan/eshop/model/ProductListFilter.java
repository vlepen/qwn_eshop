package com.garwan.eshop.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

@Builder
@Data
public class ProductListFilter {
    @NonNull
    private BigDecimal priceMin;
    @NonNull
    private BigDecimal priceMax;
    @NonNull
    private String namePart;
    private Pageable pageable;
}
