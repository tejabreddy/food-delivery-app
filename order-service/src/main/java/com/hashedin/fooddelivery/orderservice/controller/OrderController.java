package com.hashedin.fooddelivery.orderservice.controller;

import com.hashedin.fooddelivery.orderservice.model.Order;
import com.hashedin.fooddelivery.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                    @RequestParam(required = false, defaultValue = "20") Integer pageSize){
        log.info("Invoking API to fetch All Orders");
        try {
            List<Order> orders = orderService.getAllOrders(pageNumber, pageSize);
            return ResponseEntity.ok(orders);
        }
        catch(Exception e){
            log.error("Error while fetching Orders");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer orderId){
        log.info("Invoking API to fetch Order by id");
        try {
            Order order = orderService.getOrderById(orderId);
            return ResponseEntity.ok(order);
        }
        catch(Exception e){
            log.error("Error while fetching Order by id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<Order>> getOrdersByUserId(
            @PathVariable("user-id") Integer userId,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize){
        log.info("Invoking API to fetch all orders by user id");
        try {
            List<Order> orders = orderService.getOrdersByUserId(userId, pageNumber, pageSize);
            return ResponseEntity.ok(orders);
        }
        catch(Exception e){
            log.error("Error while fetching orders by user id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order, @PathVariable Integer userId){
        log.info("Invoking API to Create Order");
        try {
            order = orderService.createOrder(order, userId);
            return ResponseEntity.ok(order);
        }
        catch(Exception e){
            log.error("Error while placing Order");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        log.info("Invoking API to update Order");
        try {
            order = orderService.updateOrder(order);
            return ResponseEntity.ok(order);
        }
        catch(Exception e){
            log.error("Error while updating Order");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
