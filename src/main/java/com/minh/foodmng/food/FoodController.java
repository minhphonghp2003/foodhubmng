package com.minh.foodmng.food;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class FoodController {
    final private FoodService foodService;

    @GetMapping("/all")
    Page<Food> getAllFood(int page, int size) {
        return foodService.getAllFood(page, size);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<Food> addFood(Food food){
        Food result = foodService.addFood(food);
        return ResponseEntity.ok(result);
    }
}
