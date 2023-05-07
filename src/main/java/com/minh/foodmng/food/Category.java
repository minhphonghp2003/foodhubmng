package com.minh.foodmng.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToOne
    @JoinColumn(name = "\"imageId\"")
    private Image image;
    @ManyToMany(mappedBy = "categories")
    private List<Food> foods;
}
