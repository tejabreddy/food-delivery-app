package com.hashedin.fooddelivery.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hashedin.fooddelivery.orderservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class User {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
    private List<RestaurantReview> restaurantReviews;
    private List<MenuItemReview> itemReviews;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
