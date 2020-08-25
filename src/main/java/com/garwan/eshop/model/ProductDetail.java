package com.garwan.eshop.model;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Getter
@SuperBuilder(toBuilder = true)
public class ProductDetail extends Product{
    private String description;
    @NonNull
    private List<String> gallery;
}
