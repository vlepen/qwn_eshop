package com.garwan.eshop.service;

import com.garwan.eshop.jpa.repository.ProductDetailRepository;
import com.garwan.eshop.jpa.repository.ProductListRepository;
import com.garwan.eshop.mapper.ProductDetailMapper;
import com.garwan.eshop.mapper.ProductMapper;
import com.garwan.eshop.model.Product;
import com.garwan.eshop.model.ProductDetail;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductListRepository productListRepository;

    public ProductService(
        ProductDetailRepository productDetailRepository,
        ProductListRepository productListRepository
    ) {
        this.productDetailRepository = productDetailRepository;
        this.productListRepository = productListRepository;
    }

    public Optional<ProductDetail> findById(Long id) {
        return productDetailRepository.findById(id)
            .map(ProductDetailMapper::fromProductDetail);
    }

    public Page<Product> findByFilter(BigDecimal priceMin, BigDecimal priceMax, String namePart, Pageable pageable) {
        return productListRepository.findAllByPriceIsBetweenAndNameStartsWith(priceMin, priceMax, namePart, pageable)
            .map(ProductMapper::fromProduct);
    }
}
