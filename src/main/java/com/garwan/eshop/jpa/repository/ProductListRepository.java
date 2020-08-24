package com.garwan.eshop.jpa.repository;

import com.garwan.eshop.jpa.entity.Product;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListRepository extends CrudRepository<Product, Long> {
    Page<Product> findAllByPriceIsBetweenAndNameStartsWith(
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String name,
        Pageable pageable
    );
}
