package com.minh.foodmng.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "reviewers")
public class Review {
    @Id
    private UUID id;
    private String name;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "reviewer")
    private List<FoodReview> reviews;
}
