package com.minh.foodmng.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class UserOrderKey implements Serializable {

    private UUID productId;

    private UUID userId;
}
