package com.minh.foodmng.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);
    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);
}
