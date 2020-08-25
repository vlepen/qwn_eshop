package com.garwan.eshop.service;

import com.garwan.eshop.jpa.entity.UserEntity;
import com.garwan.eshop.jpa.repository.UserRepository;
import com.garwan.eshop.mapper.EshopUserMapper;
import com.garwan.eshop.model.CreateUserRequest;
import com.garwan.eshop.model.EshopUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void shouldCreateUserWithEncodedPassword() {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
            .username("username")
            .password("plain text password")
            .email("email@google.com")
            .build();
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(createUserRequest.getPassword())).thenReturn(encodedPassword);
        UserEntity userEntity = EshopUserMapper.toUserEntity(createUserRequest, encodedPassword);
        UserEntity expectedPersistedUserEntity = userEntity.toBuilder().id(2L).build();
        when(userRepository.save(userEntity)).thenReturn(expectedPersistedUserEntity);

        EshopUser actualResult = userService.create(createUserRequest);

        verify(userRepository).save(userEntity);
        verify(passwordEncoder).encode(createUserRequest.getPassword());
        assertThat(actualResult).isEqualTo(EshopUserMapper.fromUserEntity(expectedPersistedUserEntity));
    }
}