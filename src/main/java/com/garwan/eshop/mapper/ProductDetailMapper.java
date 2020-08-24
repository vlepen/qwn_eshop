package com.garwan.eshop.mapper;

import com.garwan.eshop.model.ProductDetail;

import static com.garwan.eshop.mapper.AnimalCategoryMapper.fromAnimalCategories;

public final class ProductDetailMapper {
    private ProductDetailMapper() {
    }

    public static ProductDetail fromProductDetail(com.garwan.eshop.jpa.entity.ProductDetail productDetail) {
        return ProductDetail.builder()
            .id(productDetail.getId())
            .name(productDetail.getName())
            .description(productDetail.getDescription())
            .price(productDetail.getPrice())
            .gallery(productDetail.getGallery())
            .animalCategories(fromAnimalCategories(productDetail.getAnimalCategories()))
            .build();
    }
}