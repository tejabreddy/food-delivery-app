package com.hashedin.fooddelivery.orderservice.repository;

import com.hashedin.fooddelivery.orderservice.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByUserUserId(Integer userId, Pageable pageable);
}
