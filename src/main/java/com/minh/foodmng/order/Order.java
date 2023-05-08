package com.minh.foodmng.order;

import com.minh.foodmng.food.Food;
import com.minh.foodmng.user.User;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "carts")
public class Order {
    @EmbeddedId
    private UserOrderKey orderId;
    @ManyToOne
    @JoinColumn(name = "\"productId\"")
    @MapsId("productId")
    private Food food;
    @ManyToOne
    @JoinColumn(name = "\"userId\"")
    @MapsId("userId")
    private User user;
    private int quantity;
    private String billing;

}
