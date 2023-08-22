package com.hashedin.fooddelivery.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "CartItems")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartItemId;

    @OneToOne
    @JoinColumn(name = "fk_item_id")
    private MenuItemEntity menuItem;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private UserEntity user;

    private Integer quantity;
    private Long subtotal;

    @CreationTimestamp
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
