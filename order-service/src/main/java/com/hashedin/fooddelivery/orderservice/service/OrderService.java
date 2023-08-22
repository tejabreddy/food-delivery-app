package com.hashedin.fooddelivery.orderservice.service;

import com.hashedin.fooddelivery.orderservice.entity.*;
import com.hashedin.fooddelivery.orderservice.exception.InvalidDataException;
import com.hashedin.fooddelivery.orderservice.model.MenuItem;
import com.hashedin.fooddelivery.orderservice.model.Order;
import com.hashedin.fooddelivery.orderservice.model.OrderItem;
import com.hashedin.fooddelivery.orderservice.repository.MenuItemRepository;
import com.hashedin.fooddelivery.orderservice.repository.OrderItemRepository;
import com.hashedin.fooddelivery.orderservice.repository.OrderRepository;
import com.hashedin.fooddelivery.orderservice.repository.UserRepository;
import com.hashedin.fooddelivery.orderservice.utils.ConversionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public List<Order> getAllOrders(Integer pageNumber, Integer pageSize){
        log.info("Fetching all Orders");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<OrderEntity> orderEntities = orderRepository.findAll(pageable).toList();
        log.info("All orders fetched successfully");
        return ConversionUtil.getOrderModelList(orderEntities);
    }

    public Order getOrderById(Integer orderId){
        log.info("fetching order details by order id: {}", orderId);
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isEmpty()){
            log.info("No order found with id: {}", orderId);
            throw new InvalidDataException("Invalid Order Id");
        }
        log.info("Order details fetched successfully");
        return ConversionUtil.getOrderModel(orderEntity.get());
    }

    public List<Order> getOrdersByUserId(Integer userId, Integer pageNumber, Integer pageSize){
        log.info("fetching users {} orders", userId);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<OrderEntity> orderEntities = orderRepository.findByUserUserId(userId, pageable);
        log.info("successfully fetched users {} orders", userId);
        return ConversionUtil.getOrderModelList(orderEntities);
    }

    public Order createOrder(Order order, Integer userId){
        log.info("placing order for user: {}", userId);
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isEmpty()) {
            throw new InvalidDataException("invalid user");
        }
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            Integer itemId = orderItem.getMenuItem().getItemId();
            Optional<MenuItemEntity> optionalMenuItem = menuItemRepository.findById(itemId);
            if (optionalMenuItem.isEmpty()) {
                throw new InvalidDataException("invalid item");
            }
            OrderItemEntity orderItemEntity = ConversionUtil.getOrderItemEntity(orderItem);
            orderItemEntity.setMenuItem(optionalMenuItem.get());
            orderItemEntity = orderItemRepository.save(orderItemEntity);
            orderItemEntities.add(orderItemEntity);
        }
        order.setStatus(OrderStatus.PLACED);
        OrderEntity orderEntity = ConversionUtil.getOrderEntity(order);
        orderEntity.setOrderItems(orderItemEntities);
        orderEntity.setUser(optionalUserEntity.get());
        orderEntity = orderRepository.save(orderEntity);
        log.info("order placed successfully with id: {}", orderEntity.getOrderId());
        return ConversionUtil.getOrderModel(orderEntity);
    }

    public Order updateOrder(Order order){
        log.info("updating order details");
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(order.getOrderId());
        if(orderEntityOptional.isEmpty()){
            log.info("Couldn't find order with id: {}", order.getOrderId());
            throw new InvalidDataException("Invalid Order id");
        }
        OrderEntity orderEntity = orderEntityOptional.get();
        orderEntity.setStatus(order.getStatus());
        orderRepository.flush();
        log.info("order updated successfully");
        return ConversionUtil.getOrderModel(orderEntity);
    }
}
