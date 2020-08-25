package com.garwan.eshop.jpa.repository;

import com.garwan.eshop.jpa.entity.ProductDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends CrudRepository<ProductDetailEntity, Long> {
}
