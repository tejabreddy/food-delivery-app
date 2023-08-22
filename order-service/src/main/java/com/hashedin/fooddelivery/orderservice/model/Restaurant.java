package com.hashedin.fooddelivery.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class Restaurant {
    private Integer restaurantId;
    @NotBlank(message = "name is mandatory")
    @Size(max = 255)
    private String name;
    @NotNull(message = "please provide restaurant starting time")
    private LocalTime startTime;
    @NotNull(message = "please provide restaurant closing time")
    private LocalTime endTime;
    @NotNull(message = "Availability is mandatory, please provide true or false")
    private Boolean isAvailable;
    private String address;
    @Pattern(regexp = "[\\d]{10}", message = "phone number should be of 10 digit")
    private String phoneNumber;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private List<MenuItem> menuItems;
    private List<RestaurantReview> restaurantReviews;
}
