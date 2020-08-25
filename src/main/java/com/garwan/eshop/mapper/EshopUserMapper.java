package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.UserEntity;
import com.garwan.eshop.model.CreateUserRequest;
import com.garwan.eshop.model.EshopUser;
import java.util.ArrayList;

public final class EshopUserMapper {
    private EshopUserMapper() {
    }

    public static UserEntity toUserEntity(CreateUserRequest createUserRequest, String encodedPassword) {
        return UserEntity.builder()
            .username(createUserRequest.getUsername())
            .password(encodedPassword)
            .email(createUserRequest.getEmail())
            .build();
    }

    public static EshopUser fromUserEntity(UserEntity user) {
        return new EshopUser(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            new ArrayList<>(),
            user.getEmail()
        );
    }
}
