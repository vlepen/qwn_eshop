package com.garwan.eshop.service;

import com.garwan.eshop.jpa.entity.UserEntity;
import com.garwan.eshop.jpa.repository.UserRepository;
import com.garwan.eshop.model.EshopUser;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public EshopUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userCandidate = userRepository.findByUsername(username);
        if (!userCandidate.isPresent()) {
            throw new UsernameNotFoundException(format("User %s not found ", username));
        }
        UserEntity userEntity = userCandidate.get();
        return new EshopUser(
            userEntity.getId(),
            userEntity.getUsername(),
            userEntity.getPassword(),
            new ArrayList<>(),
            userEntity.getEmail()
        );
    }
}
