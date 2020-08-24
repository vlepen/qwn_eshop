package com.garwan.eshop.service;

import com.garwan.eshop.jpa.repository.UserRepository;
import com.garwan.eshop.mapper.EshopUserMapper;
import com.garwan.eshop.model.CreateUserRequest;
import com.garwan.eshop.model.EshopUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public EshopUser create(CreateUserRequest createUserRequest) {
        return EshopUserMapper.fromUser(userRepository.save(EshopUserMapper.toEshopUser(
            createUserRequest,
            bcryptEncoder.encode(createUserRequest.getPassword())
        )));
    }
}
