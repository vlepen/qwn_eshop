package com.garwan.eshop.service;

import com.garwan.eshop.jpa.entity.User;
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
        Optional<User> userCandidate = userRepository.findByUsername(username);
        if (!userCandidate.isPresent()) {
            throw new UsernameNotFoundException(format("User %s not found ", username));
        }
        User user = userCandidate.get();
        return new EshopUser(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            new ArrayList<>(),
            user.getEmail()
        );
    }
}
