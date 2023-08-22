package com.hashedin.fooddelivery.orderservice.repository;

import com.hashedin.fooddelivery.orderservice.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
}
