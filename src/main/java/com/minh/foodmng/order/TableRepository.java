package com.minh.foodmng.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<Table,Integer> {
    Optional<Table> findById(Integer id);
    Page<Table> findAll(Pageable pageable);
}
