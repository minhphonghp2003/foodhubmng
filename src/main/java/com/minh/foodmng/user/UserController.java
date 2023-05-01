package com.minh.foodmng.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

}
