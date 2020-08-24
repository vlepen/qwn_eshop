package com.garwan.eshop.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "animal_category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class AnimalCategory {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
}
