package com.minh.foodmng.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    String token;
}
