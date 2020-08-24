package com.garwan.eshop.jpa.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = 'eshop_user_order')
public class UserOrder {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @EqualsAndHashCode
    public static class OrderProductPK implements Serializable {
        protected Long userId;
        protected Order order;
    }
}
