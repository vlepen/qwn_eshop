package com.garwan.eshop.controller;

import com.garwan.eshop.config.JwtAuthenticationEntryPoint;
import com.garwan.eshop.service.JwtTokenUtil;
import com.garwan.eshop.service.JwtUserDetailsService;
import com.garwan.eshop.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestControllerTestConfig {
    @Bean
    public JwtUserDetailsService jwtUserDetailsService() {
        return Mockito.mock(JwtUserDetailsService.class);
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil("i_like_java");
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }
}
