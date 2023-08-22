package com.hashedin.fooddelivery.orderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @OneToMany
    @JoinColumn(name = "fk_order_id")
    private List<OrderItemEntity> orderItems;

    private LocalDate orderDate;
    private Long totalAmount;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private UserEntity user;

    @Enumerated
    private OrderStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
