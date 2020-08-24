package com.garwan.eshop.jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.SEQUENCE;

@Table(name = "eshop_order")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(generator = "order_id_generator", strategy = SEQUENCE)
    @SequenceGenerator(name = "order_id_generator", sequenceName = "eshop_order_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderProduct> items;
    @Column
    private LocalDateTime time;

    public void addItems(List<OrderProduct> orderProducts) {
        orderProducts.forEach(orderProduct -> orderProduct.setOrder(this));
        if (items == null) {
            items = new ArrayList<>();
        }
        items.addAll(orderProducts);
    }
}
