package com.hashedin.fooddelivery.orderservice.repository;

import com.hashedin.fooddelivery.orderservice.entity.CartItemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {

    List<CartItemEntity> findByUserUserId(Integer userId, Pageable pageable);
}
