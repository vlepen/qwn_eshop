package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.ProductDetailEntity;
import com.garwan.eshop.model.ProductDetail;

import static com.garwan.eshop.mapper.AnimalCategoryMapper.fromAnimalCategoryEntities;

public final class ProductDetailMapper {
    private ProductDetailMapper() {
    }

    public static ProductDetail fromProductDetailEntity(ProductDetailEntity productDetail) {
        return ProductDetail.builder()
            .id(productDetail.getId())
            .name(productDetail.getName())
            .description(productDetail.getDescription())
            .price(productDetail.getPrice())
            .gallery(productDetail.getGallery())
            .animalCategories(fromAnimalCategoryEntities(productDetail.getAnimalCategories()))
            .build();
    }
}