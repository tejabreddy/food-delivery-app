package com.hashedin.fooddelivery.orderservice.controller;

import com.hashedin.fooddelivery.orderservice.model.CartItem;
import com.hashedin.fooddelivery.orderservice.service.CartService;
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
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public ResponseEntity<CartItem> addCartItem(@Valid @RequestBody CartItem cartItem, @RequestParam Integer userId){
        log.info("Invoking API to add item to cart");
        try {
            log.info("userid: {}", userId);
            cartItem = cartService.addCartItem(cartItem, userId);
            log.info("Item added successfully to cart with id {}", cartItem.getCartItemId());
            return ResponseEntity.ok(cartItem);
        }
        catch(Exception e){
            log.error("Error while adding item to cart");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("{cart-id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable("cart-id") Integer cartId){
        log.info("Invoking API to fetch cart item id");
        try {
            CartItem cartItem = cartService.getCartItemById(cartId);
            return ResponseEntity.ok(cartItem);
        }
        catch(Exception e){
            log.error("Error while fetching cart item by id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<List<CartItem>> getCartItemsByUserId(@PathVariable("user-id") Integer userId,
                                                               @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(required = false, defaultValue = "20") Integer pageSize){
        log.info("Invoking API to fetch cart by user id");
        try {
            List<CartItem> cartItemList = cartService.getCartItemsByUserId(userId, pageNumber, pageSize);
            return ResponseEntity.ok(cartItemList);
        }
        catch(Exception e){
            log.error("Error while fetching cart items by user id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PutMapping
    public ResponseEntity<CartItem> updateCartItem(@Valid @RequestBody CartItem cartItem){
        log.info("Invoking API to update cart item");
        try {
           cartItem = cartService.updateCartItem(cartItem);
            return ResponseEntity.ok(cartItem);
        }
        catch(Exception e){
            log.error("Error while fetching cart items by user id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<String> updateCartItem(@PathVariable Integer cartItemId){
        log.info("Invoking API to remove item from user's cart");
        try {
            cartService.removeCartItem(cartItemId);
            return ResponseEntity.ok("Item removed from cart successfully");
        }
        catch(Exception e){
            log.error("Error while fetching cart items by user id");
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
