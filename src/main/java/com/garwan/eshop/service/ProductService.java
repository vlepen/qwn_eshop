package com.garwan.eshop.service;

import com.garwan.eshop.jpa.repository.ProductDetailRepository;
import com.garwan.eshop.jpa.repository.ProductRepository;
import com.garwan.eshop.mapper.ProductDetailMapper;
import com.garwan.eshop.mapper.ProductMapper;
import com.garwan.eshop.model.Product;
import com.garwan.eshop.model.ProductDetail;
import com.garwan.eshop.model.ProductListFilter;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductRepository productRepository;

    public ProductService(
        ProductDetailRepository productDetailRepository,
        ProductRepository productRepository
    ) {
        this.productDetailRepository = productDetailRepository;
        this.productRepository = productRepository;
    }

    public Optional<ProductDetail> findById(Long id) {
        return productDetailRepository.findById(id)
            .map(ProductDetailMapper::fromProductDetailEntity);
    }

    public Page<Product> findByFilter(ProductListFilter productListFilter) {
        return productRepository.findAllByPriceIsBetweenAndNameStartsWith(
            productListFilter.getPriceMin(),
            productListFilter.getPriceMax(),
            productListFilter.getNamePart(),
            productListFilter.getPageable()
        )
            .map(ProductMapper::fromProductEntity);
    }
}
