package com.minh.foodmng.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.minh.foodmng.food.CreatedUpdated;
import com.minh.foodmng.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@jakarta.persistence.Table(name = "table_bookings")
@ToString(exclude = { "user" })
@JsonIgnoreProperties("user")

@Data
public class Table extends CreatedUpdated {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "\"userId\"",nullable = false)
    private User user;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private LocalTime time;
    @Column(nullable = false)
    private int adult;
    @Column(nullable = false)
    private int kid;
    private String message;
    public UUID getUserId(){
        return user.getId();
    }
    public String getUserName(){
        return user.getUsername();
    }

    public String getUserEmail(){
        return user.getEmail();
    }
    public String getUserPhone(){
        return user.getPhone();
    }
}
