package com.garwan.eshop.jpa.repository;

import com.garwan.eshop.jpa.entity.OrderEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByUserId(Long userId);
}
