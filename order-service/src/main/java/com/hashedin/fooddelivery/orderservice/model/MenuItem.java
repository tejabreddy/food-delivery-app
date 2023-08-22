package com.hashedin.fooddelivery.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hashedin.fooddelivery.orderservice.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class MenuItem {
    private Integer itemId;
    @NotBlank(message = "name is mandatory")
    @Size(max = 255)
    private String name;
    @NotBlank(message = "description is mandatory")
    private String description;
    @NotNull(message = "price is mandatory")
    private Integer price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Category category;
    private List<MenuItemReview> itemReviews = new ArrayList<>();
}
