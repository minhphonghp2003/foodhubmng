package com.minh.foodmng.food;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class FoodService {
    final private FoodRepository foodRepository;
    Page<Food> getAllFood(int page, int size) {
        return foodRepository.findAll(PageRequest.of(page,size));
    }

    public Food addFood(Food food) {
        foodRepository.save(food);
        return food;
    }
}
