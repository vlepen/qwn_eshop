package com.garwan.eshop.mapper;

import com.garwan.eshop.model.CreateUserRequest;
import com.garwan.eshop.model.EshopUser;
import java.util.ArrayList;

public final class EshopUserMapper {
    private EshopUserMapper() {
    }

    public static com.garwan.eshop.jpa.entity.User toEshopUser(
        CreateUserRequest createUserRequest,
        String encodedPassword
    ) {
        return com.garwan.eshop.jpa.entity.User.builder()
            .username(createUserRequest.getUsername())
            .password(encodedPassword)
            .email(createUserRequest.getEmail())
            .build();
    }

    public static EshopUser fromUser(com.garwan.eshop.jpa.entity.User user) {
        return new EshopUser(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            new ArrayList<>(),
            user.getEmail()
        );
    }
}
