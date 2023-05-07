package com.minh.foodmng.food;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class FoodController {
    final private FoodService foodService;

    @GetMapping("/all")
    Page<Food> getAllFood(int page, int size) throws IOException {
        return foodService.getAllFood(page, size);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin')")
    ResponseEntity<Food> addFood(Food food){
        Food result = foodService.addFood(food);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reviews")
    ResponseEntity<Page<FoodReview>> getAllReviews(int page, int size){
        Page<FoodReview> reviews = foodService.getAllReview(page,size);
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("/allcategories")
    List<Category> getAllCategories(){
        return foodService.getAllCategories();
    }
    @GetMapping("/category/{id}")
    Category getCategoryBy(@PathVariable Integer id){
        return foodService.getCategoryBy(id);
    }
}
