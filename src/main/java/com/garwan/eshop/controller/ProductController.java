package com.garwan.eshop.controller;

import com.garwan.eshop.model.Product;
import com.garwan.eshop.model.ProductDetail;
import com.garwan.eshop.model.ProductListFilter;
import com.garwan.eshop.service.ProductService;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductDetail> getProduct(@NotNull @Positive @PathVariable Long id) {
        return ResponseEntity.of(productService.findById(id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Product>> getProductsByFilter(
        @NotNull @Positive @RequestParam("priceMin") BigDecimal priceMin,
        @NotNull @Positive @RequestParam("priceMax") BigDecimal priceMax,
        @NotBlank @RequestParam("namePart") String namePart,
        Pageable pageable
    ) {
        return ResponseEntity.ok(productService.findByFilter(ProductListFilter.builder()
            .priceMin(priceMin)
            .priceMax(priceMax)
            .namePart(namePart)
            .pageable(pageable)
            .build()));
    }
}
