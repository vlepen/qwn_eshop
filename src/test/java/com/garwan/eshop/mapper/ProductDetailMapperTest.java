package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.AnimalCategoryEntity;
import com.garwan.eshop.jpa.entity.ProductDetailEntity;
import com.garwan.eshop.model.ProductDetail;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static com.garwan.eshop.mapper.AnimalCategoryMapper.fromAnimalCategoryEntities;
import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

class ProductDetailMapperTest {
    @Test
    void shouldMapFromProductDetailEntity() {
        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
            .id(1L)
            .name("name")
            .description("description")
            .price(new BigDecimal("332.4"))
            .gallery(asList("url1", "url2"))
            .animalCategories(asList(animalCategoryEntity(2L), animalCategoryEntity(4L)))
            .build();

        ProductDetail actualResult = ProductDetailMapper.fromProductDetailEntity(productDetailEntity);

        assertThat(actualResult).isEqualTo(ProductDetail.builder()
            .id(productDetailEntity.getId())
            .name(productDetailEntity.getName())
            .description(productDetailEntity.getDescription())
            .price(productDetailEntity.getPrice())
            .gallery(productDetailEntity.getGallery())
            .animalCategories(fromAnimalCategoryEntities(productDetailEntity.getAnimalCategories()))
            .build());
    }

    @Test
    void shouldMapFromProductDetailEntityWhenGalleryAndAnimalCategoriesAreEmpty() {
        ProductDetailEntity productDetailEntity = ProductDetailEntity.builder()
            .id(1L)
            .name("name")
            .description("description")
            .price(new BigDecimal("332.4"))
            .gallery(emptyList())
            .animalCategories(emptyList())
            .build();

        ProductDetail actualResult = ProductDetailMapper.fromProductDetailEntity(productDetailEntity);

        assertThat(actualResult).isEqualTo(ProductDetail.builder()
            .id(productDetailEntity.getId())
            .name(productDetailEntity.getName())
            .description(productDetailEntity.getDescription())
            .price(productDetailEntity.getPrice())
            .gallery(emptyList())
            .animalCategories(emptyList())
            .build());
    }

    private static AnimalCategoryEntity animalCategoryEntity(Long id) {
        return AnimalCategoryEntity.builder().id(id).name("category" + id).build();
    }
}