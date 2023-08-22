package com.hashedin.fooddelivery.orderservice.repository;

import com.hashedin.fooddelivery.orderservice.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Integer> {

}
