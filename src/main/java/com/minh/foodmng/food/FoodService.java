package com.minh.foodmng.food;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Service
@Data
@RequiredArgsConstructor
public class FoodService {
    final private FoodRepository foodRepository;
    final private ReviewRepository reviewRepository;

    Page<Food> getAllFood(int page, int size) throws IOException {
        Page<Food> foods = foodRepository.findAll(PageRequest.of(page, size));

        return foods;
    }

    public Food addFood(Food food) {
        foodRepository.save(food);
        return food;
    }

    public Page<FoodReview> getAllReview(int page, int size) {
        return reviewRepository.findAll(PageRequest.of(page, size));
    }
}
