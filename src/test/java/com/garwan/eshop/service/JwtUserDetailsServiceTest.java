package com.garwan.eshop.service;

import com.garwan.eshop.jpa.entity.UserEntity;
import com.garwan.eshop.jpa.repository.UserRepository;
import com.garwan.eshop.model.EshopUser;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.google.common.truth.Truth.assertThat;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtUserDetailsServiceTest {
    JwtUserDetailsService jwtUserDetailsService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        jwtUserDetailsService = new JwtUserDetailsService(userRepository);
    }

    @Test
    void shouldLoadUserByUsername() {
        String username = "username";
        UserEntity expectedUserEntity = UserEntity.builder()
            .id(1L)
            .username(username)
            .password("password")
            .email("email@email.com")
            .build();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(expectedUserEntity));

        EshopUser actualResult = jwtUserDetailsService.loadUserByUsername(username);

        verify(userRepository).findByUsername(username);
        assertThat(actualResult).isEqualTo(new EshopUser(
            expectedUserEntity.getId(),
            expectedUserEntity.getUsername(),
            expectedUserEntity.getPassword(),
            new ArrayList<>(),
            expectedUserEntity.getEmail()
        ));
    }

    @Test
    void shouldThrowExceptionWhenLoadingUserByUsernameAndNoneIsFound() {
        String username = "username";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        UsernameNotFoundException actualResult =
            assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername(username));

        assertThat(actualResult.getMessage()).isEqualTo(format("User %s not found ", username));
    }
}