package com.garwan.eshop.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "eshop_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(generator = "eshop_user_id_generator", strategy = SEQUENCE)
    @SequenceGenerator(name = "eshop_user_id_generator", sequenceName = "eshop_user_id_seq", allocationSize = 1)
    private Long id;
    private String username;
    private String password;
    private String email;
}
