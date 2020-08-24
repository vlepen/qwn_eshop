package com.garwan.eshop.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@IdClass(OrderProduct.OrderProductPK.class)
@Table(name = "order_product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class OrderProduct {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    private BigDecimal price;
    private Integer count;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @EqualsAndHashCode
    public static class OrderProductPK implements Serializable {
        protected Long productId;
        protected Order order;
    }
}
