package com.hashedin.fooddelivery.orderservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hashedin.fooddelivery.orderservice.entity.OrderStatus;
import com.hashedin.fooddelivery.orderservice.entity.RestaurantEntity;
import com.hashedin.fooddelivery.orderservice.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@ToString
public class Order {
    private Integer orderId;
    @NotNull(message = "order items are mandatory")
    private List<OrderItem> orderItems;
    private LocalDate orderDate;
    private User user;
    @NotNull(message = "total amount is mandatory")
    private Long totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
