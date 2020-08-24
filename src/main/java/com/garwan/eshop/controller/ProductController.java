package com.garwan.eshop.controller;

import com.garwan.eshop.model.ProductDetail;
import com.garwan.eshop.service.ProductService;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
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
}
