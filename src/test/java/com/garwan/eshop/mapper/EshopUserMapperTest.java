package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.UserEntity;
import com.garwan.eshop.model.CreateUserRequest;
import com.garwan.eshop.model.EshopUser;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class EshopUserMapperTest {
    @Test
    void shouldMapFromUserEntity() {
        UserEntity userEntity = UserEntity.builder()
            .email("email")
            .id(2L)
            .password("password")
            .username("username")
            .build();

        EshopUser actualResult = EshopUserMapper.fromUserEntity(userEntity);

        assertThat(actualResult).isEqualTo(new EshopUser(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getPassword(),
            new ArrayList<>(),
            userEntity.getEmail()
        ));
    }

    @Test
    void shouldMapToUserEntity() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder().build();
        String encodedPassword = "encoded_password";

        UserEntity actualResult = EshopUserMapper.toUserEntity(createUserRequest, encodedPassword);

        assertThat(actualResult).isEqualTo(UserEntity.builder()
            .username(createUserRequest.getUsername())
            .password(encodedPassword)
            .email(createUserRequest.getEmail())
            .build());
    }

}