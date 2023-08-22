package com.hashedin.fooddelivery.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@ToString
public class OrderItem {
    private Integer orderItemId;
    @NotBlank(message = "menu item is mandatory")
    private MenuItem menuItem;
    @NotNull(message = "quantity is mandatory")
    private Integer quantity;
    @NotNull(message = "subtotal amount is mandatory")
    private Long subtotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
