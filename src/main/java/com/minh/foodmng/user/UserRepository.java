package com.minh.foodmng.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);
    List<User> findAll();

    User findByUsername(String username);
}
