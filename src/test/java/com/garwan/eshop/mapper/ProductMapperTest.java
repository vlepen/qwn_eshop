package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.AnimalCategoryEntity;
import com.garwan.eshop.jpa.entity.ProductEntity;
import com.garwan.eshop.model.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static com.garwan.eshop.mapper.AnimalCategoryMapper.fromAnimalCategoryEntities;
import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

class ProductMapperTest {
    @Test
    void shouldMapFromProductEntity() {
        ProductEntity productEntity = ProductEntity.builder()
            .id(1L)
            .name("name")
            .price(new BigDecimal("223.4"))
            .animalCategories(asList(animalCategoryEntity(2L), animalCategoryEntity(3L)))
            .build();

        Product actualResult = ProductMapper.fromProductEntity(productEntity);

        assertThat(actualResult).isEqualTo(Product.builder()
            .id(productEntity.getId())
            .name(productEntity.getName())
            .price(productEntity.getPrice())
            .animalCategories(fromAnimalCategoryEntities(productEntity.getAnimalCategories()))
            .build());
    }

    @Test
    void shouldMapFromProductEntityWithEmptyAnimalCategories() {
        ProductEntity productEntity = ProductEntity.builder()
            .id(1L)
            .name("name")
            .price(new BigDecimal("223.4"))
            .animalCategories(emptyList())
            .build();

        Product actualResult = ProductMapper.fromProductEntity(productEntity);

        assertThat(actualResult).isEqualTo(Product.builder()
            .id(productEntity.getId())
            .name(productEntity.getName())
            .price(productEntity.getPrice())
            .animalCategories(emptyList())
            .build());
    }

    private static AnimalCategoryEntity animalCategoryEntity(Long id) {
        return AnimalCategoryEntity.builder().id(id).name("category" + id).build();
    }
}