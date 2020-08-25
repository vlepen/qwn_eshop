package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.ProductEntity;
import com.garwan.eshop.model.Product;

import static com.garwan.eshop.mapper.AnimalCategoryMapper.fromAnimalCategoryEntities;

public final class ProductMapper {
    private ProductMapper() {
    }

    public static Product fromProductEntity(ProductEntity product) {
        return Product.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .animalCategories(fromAnimalCategoryEntities(product.getAnimalCategories()))
            .build();
    }
}
