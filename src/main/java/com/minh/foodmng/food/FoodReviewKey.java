package com.minh.foodmng.food;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class FoodReviewKey implements Serializable {
    @Column(name = "\"productId\"")
    private UUID productId;
    @Column(name = "\"reviewerId\"")
    private UUID reviewerId;
}
