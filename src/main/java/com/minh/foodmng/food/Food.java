package com.minh.foodmng.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "products")
public class Food  extends CreatedUpdated{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String sku;
    @Column(nullable = false)
    private String price;
    @Column(nullable = false)
    private String description;
    @ManyToMany
    @JoinTable(
            name = "product_images",
            joinColumns = @JoinColumn(name = "\"productId\""),
            inverseJoinColumns = @JoinColumn(name = "\"imageId\"")
    )
    private List<Image> images;

    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<FoodReview> reviews;



}
