package com.hashedin.fooddelivery.orderservice.utils;

import com.hashedin.fooddelivery.orderservice.entity.*;
import com.hashedin.fooddelivery.orderservice.model.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConversionUtil {

    public static OrderEntity getOrderEntity(Order order){
        return OrderEntity.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .orderItems(getOrderItemEntityList(order.getOrderItems()))
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static Order getOrderModel(OrderEntity order){
        return Order.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .orderItems(getOrderItemModelList(order.getOrderItems()))
                .user(User.builder().userId(order.getUser().getUserId()).build())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static List<OrderEntity> getOrderEntityList(List<Order> orders){
        if(CollectionUtils.isEmpty(orders)) return new ArrayList<>();
        return orders.stream()
                .map(ConversionUtil::getOrderEntity)
                .collect(Collectors.toList());
    }

    public static List<Order> getOrderModelList(List<OrderEntity> orderEntities){
        if(CollectionUtils.isEmpty(orderEntities)) return new ArrayList<>();
        return orderEntities.stream()
                .map(ConversionUtil::getOrderModel)
                .collect(Collectors.toList());
    }

    public static OrderItem getOrderItemModel(OrderItemEntity orderItem){
        return OrderItem.builder()
                .orderItemId(orderItem.getOrderItemId())
                .menuItem(MenuItem.builder().itemId(orderItem.getMenuItem().getItemId()).build())
                .quantity(orderItem.getQuantity())
                .subtotal(orderItem.getSubtotal())
                .createdAt(orderItem.getCreatedAt())
                .updatedAt(orderItem.getUpdatedAt())
                .build();

    }

    public static OrderItemEntity getOrderItemEntity(OrderItem orderItem){
        return OrderItemEntity.builder()
                .orderItemId(orderItem.getOrderItemId())
                .menuItem(MenuItemEntity.builder().itemId(orderItem.getMenuItem().getItemId()).build())
                .quantity(orderItem.getQuantity())
                .subtotal(orderItem.getSubtotal())
                .createdAt(orderItem.getCreatedAt())
                .updatedAt(orderItem.getUpdatedAt())
                .build();

    }

    public static List<OrderItemEntity> getOrderItemEntityList(List<OrderItem> orderItemList){
        if(CollectionUtils.isEmpty(orderItemList)) return new ArrayList<>();
        return orderItemList.stream()
                .map(ConversionUtil::getOrderItemEntity)
                .collect(Collectors.toList());
    }

    public static List<OrderItem> getOrderItemModelList(List<OrderItemEntity> orderItemEntities){
        if(CollectionUtils.isEmpty(orderItemEntities)) return new ArrayList<>();
        return orderItemEntities.stream()
                .map(ConversionUtil::getOrderItemModel)
                .collect(Collectors.toList());
    }

    public static CartItemEntity getCartItemEntity(CartItem cartItem){
        return CartItemEntity.builder()
                .cartItemId(cartItem.getCartItemId())
                .menuItem(MenuItemEntity.builder().itemId(cartItem.getMenuItem().getItemId()).build())
//                .user(UserEntity.builder().userId(cartItem.getUser().getUserId()).build())
                .quantity(cartItem.getQuantity())
                .subtotal(cartItem.getSubtotal())
                .createAt(cartItem.getCreateAt())
                .updatedAt(cartItem.getUpdatedAt())
                .build();
    }

    public static CartItem getCartItemModel(CartItemEntity cartItemEntity){
        return CartItem.builder()
                .cartItemId(cartItemEntity.getCartItemId())
                .menuItem(MenuItem.builder().itemId(cartItemEntity.getMenuItem().getItemId()).build())
                .user(User.builder().userId(cartItemEntity.getUser().getUserId()).build())
                .quantity(cartItemEntity.getQuantity())
                .subtotal(cartItemEntity.getSubtotal())
                .createAt(cartItemEntity.getCreateAt())
                .updatedAt(cartItemEntity.getUpdatedAt())
                .build();
    }

    public static List<CartItemEntity> getCartItemEntityList(List<CartItem> cartItemList){
        if(CollectionUtils.isEmpty(cartItemList)) return new ArrayList<>();
        return cartItemList.stream()
                .map(ConversionUtil::getCartItemEntity)
                .collect(Collectors.toList());
    }

    public static List<CartItem> getCartItemModelList(List<CartItemEntity> cartItemEntityList){
        if(CollectionUtils.isEmpty(cartItemEntityList)) return new ArrayList<>();
        return cartItemEntityList.stream()
                .map(ConversionUtil::getCartItemModel)
                .collect(Collectors.toList());
    }
}
