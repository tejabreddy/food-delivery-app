package com.hashedin.fooddelivery.orderservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class RestaurantReview {
    private Integer reviewId;
    @NotBlank(message = "rating is mandatory, please rate restaurant from 1-10")
    private Integer rating;
    @NotBlank(message = "review comments are mandatory, please add you are comments ")
    private String reviewComments;
    private LocalDateTime createdAt;
}
