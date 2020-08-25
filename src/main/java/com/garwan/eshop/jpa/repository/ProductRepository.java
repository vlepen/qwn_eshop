package com.garwan.eshop.jpa.repository;

import com.garwan.eshop.jpa.entity.ProductEntity;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByPriceIsBetweenAndNameStartsWith(
        BigDecimal priceMin,
        BigDecimal priceMax,
        String name,
        Pageable pageable
    );
}
