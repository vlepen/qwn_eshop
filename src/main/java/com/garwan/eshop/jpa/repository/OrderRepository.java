package com.garwan.eshop.jpa.repository;

import com.garwan.eshop.jpa.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
