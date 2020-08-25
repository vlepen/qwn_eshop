package com.garwan.eshop.jpa.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Builder
@Data
public class ProductDetailEntity {
    @Id
    @GeneratedValue(generator = "product_id_generator", strategy = SEQUENCE)
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    @NonNull
    @ManyToMany
    @JoinTable(
        name = "product_animal_category",
        joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "animal_category_id", referencedColumnName = "id")
    )
    private List<AnimalCategoryEntity> animalCategories;
    private BigDecimal price;
    private String description;
    @NonNull
    @ElementCollection
    @CollectionTable(name = "product_gallery", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "url")
    private List<String> gallery;
}
