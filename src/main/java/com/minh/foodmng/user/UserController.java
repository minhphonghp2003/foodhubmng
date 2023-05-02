package com.minh.foodmng.user;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/")
    public ResponseEntity<String> deleteUser(String id){
        userService.deleteUserBy(UUID.fromString(id));
        return ResponseEntity.ok("Done");
    }
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('staff', 'admin')")
    public ResponseEntity<Page<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

}
