package com.garwan.eshop.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@EqualsAndHashCode
@Getter
@SuperBuilder(toBuilder = true)
public class Product {
    private Long id;
    private String name;
    @NonNull
    private List<AnimalCategory> animalCategories;
    private BigDecimal price;
}
