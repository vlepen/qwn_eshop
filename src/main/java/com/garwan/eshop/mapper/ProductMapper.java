package com.garwan.eshop.mapper;

import com.garwan.eshop.model.Product;

import static com.garwan.eshop.mapper.AnimalCategoryMapper.fromAnimalCategories;

public final class ProductMapper {
    private ProductMapper() {
    }

    public static Product fromProduct(com.garwan.eshop.jpa.entity.Product product) {
        return Product.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .animalCategories(fromAnimalCategories(product.getAnimalCategories()))
            .build();
    }
}
