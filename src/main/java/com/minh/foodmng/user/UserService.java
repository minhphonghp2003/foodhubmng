package com.minh.foodmng.user;

import com.minh.foodmng.config.jwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    final private UserRepository userRepository;
    final private AuthenticationManager authenticationManager;
    final private jwtUtil jwtUtil;

    Page<User> getAllUser(){

        return userRepository.findAll(PageRequest.of(1,4));
    }
    void deleteUserBy(UUID id){
        userRepository.deleteById(id);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username, request.password));
        User user = userRepository.findByUsername(request.getUsername());
        String token =jwtUtil.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
