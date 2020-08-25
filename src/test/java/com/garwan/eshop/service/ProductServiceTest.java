package com.garwan.eshop.service;

import com.garwan.eshop.jpa.entity.AnimalCategoryEntity;
import com.garwan.eshop.jpa.entity.ProductDetailEntity;
import com.garwan.eshop.jpa.entity.ProductEntity;
import com.garwan.eshop.jpa.repository.ProductDetailRepository;
import com.garwan.eshop.jpa.repository.ProductRepository;
import com.garwan.eshop.mapper.ProductDetailMapper;
import com.garwan.eshop.mapper.ProductMapper;
import com.garwan.eshop.model.Product;
import com.garwan.eshop.model.ProductDetail;
import com.garwan.eshop.model.ProductListFilter;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private ProductService productService;
    @Mock
    private ProductDetailRepository productDetailRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productService = new ProductService(productDetailRepository, productRepository);
    }

    @Test
    void shouldFindById() {
        Long id = 1L;
        ProductDetailEntity expectedProductDetailEntity = ProductDetailEntity.builder()
            .id(id)
            .name("name")
            .description("description")
            .price(new BigDecimal("332.4"))
            .gallery(asList("url1", "url2"))
            .animalCategories(asList(animalCategoryEntity(2L), animalCategoryEntity(4L)))
            .build();
        when(productDetailRepository.findById(id)).thenReturn(of(expectedProductDetailEntity));

        Optional<ProductDetail> actualResult = productService.findById(id);

        verify(productDetailRepository).findById(id);
        assertThat(actualResult).hasValue(ProductDetailMapper.fromProductDetailEntity(expectedProductDetailEntity));
    }

    @Test
    void shouldReturnEmptyOptionalWhenFindingByIdAndNoEntityIsFound() {
        Long id = 1L;
        when(productDetailRepository.findById(id)).thenReturn(empty());

        Optional<ProductDetail> actualResult = productService.findById(id);

        assertThat(actualResult).isEmpty();
    }

    @Test
    void shouldFindByFilter() {
        ProductListFilter productListFilter = ProductListFilter.builder()
            .priceMin(new BigDecimal("2"))
            .priceMax(new BigDecimal("10"))
            .namePart("namePart")
            .pageable(PageRequest.of(0, 10))
            .build();
        ProductEntity productEntity1 = productEntity(1L);
        ProductEntity productEntity2 = productEntity(2L);
        Page<ProductEntity> expectedProducts =
            new PageImpl(asList(productEntity1, productEntity2), productListFilter.getPageable(), 10);
        when(productRepository.findAllByPriceIsBetweenAndNameStartsWith(
            any(BigDecimal.class),
            any(BigDecimal.class),
            any(String.class),
            any(PageRequest.class)
        ))
            .thenReturn(expectedProducts);

        Page<Product> actualResult = productService.findByFilter(productListFilter);

        verify(productRepository).findAllByPriceIsBetweenAndNameStartsWith(
            productListFilter.getPriceMin(),
            productListFilter.getPriceMax(),
            productListFilter.getNamePart(),
            productListFilter.getPageable()
        );
        assertThat(actualResult).isEqualTo(expectedProducts.map(ProductMapper::fromProductEntity));
    }

    private ProductEntity productEntity(long id) {
        return ProductEntity.builder()
            .id(id)
            .name("name" + id)
            .price(new BigDecimal(id + "3.21"))
            .animalCategories(asList(animalCategoryEntity(id + 1), animalCategoryEntity(id + 2)))
            .build();
    }

    private static AnimalCategoryEntity animalCategoryEntity(Long id) {
        return AnimalCategoryEntity.builder().id(id).name("category" + id).build();
    }
}