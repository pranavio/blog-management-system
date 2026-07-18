package com.example.blogkar.security;

import com.example.blogkar.user.entity.User;
import com.example.blogkar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByEmail(username).orElseThrow();
        return new CustomUserDetails(user);
    }
}
