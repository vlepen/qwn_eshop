package com.garwan.eshop.model;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class EshopUser extends User {
    private final Long id;
    private final String email;

    public EshopUser(
        Long id,
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String email
    ) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
    }
}
