package com.minh.foodmng.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "product_reviews")
public class FoodReview {
    @EmbeddedId
    FoodReviewKey id;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "\"productId\"")
    private Food food;

    @ManyToOne
    @MapsId("reviewerId")
    @JoinColumn(name = "\"reviewerId\"")
    private Review reviewer;
    private int rating;
    private String content;
}
