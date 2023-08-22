package com.hashedin.fooddelivery.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
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
public class CartItem {
    private Integer cartItemId;
    @NotNull(message = "please provide menu item id")
    private MenuItem menuItem;
    private User user;
    @NotNull(message = "please specify item quantity")
    private Integer quantity;
    @NotNull(message = "please provide subtotal amount")
    private Long subtotal;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
