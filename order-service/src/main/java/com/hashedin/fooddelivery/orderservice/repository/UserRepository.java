package com.hashedin.fooddelivery.orderservice.repository;

import com.hashedin.fooddelivery.orderservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
