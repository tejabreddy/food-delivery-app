package com.hashedin.fooddelivery.orderservice.service;

import com.hashedin.fooddelivery.orderservice.entity.CartItemEntity;
import com.hashedin.fooddelivery.orderservice.entity.MenuItemEntity;
import com.hashedin.fooddelivery.orderservice.entity.UserEntity;
import com.hashedin.fooddelivery.orderservice.exception.InvalidDataException;
import com.hashedin.fooddelivery.orderservice.model.CartItem;
import com.hashedin.fooddelivery.orderservice.repository.CartItemRepository;
import com.hashedin.fooddelivery.orderservice.repository.MenuItemRepository;
import com.hashedin.fooddelivery.orderservice.repository.UserRepository;
import com.hashedin.fooddelivery.orderservice.utils.ConversionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CartService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public CartItem getCartItemById(Integer cartId){
        log.info("Fetching cart item by id");
        Optional<CartItemEntity> cartItemEntity = cartItemRepository.findById(cartId);
        if(cartItemEntity.isEmpty()){
            log.error("Invalid cart Item Id");
            throw new InvalidDataException("No cart item found with given Id");
        }
        log.info("cart item fetched successfully");
        return ConversionUtil.getCartItemModel(cartItemEntity.get());
    }

    public List<CartItem> getCartItemsByUserId(Integer userId, Integer pageNumber, Integer pageSize){
        log.info("Fetching cart by user id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findByUserUserId(userId, pageable);
        log.info("user's cart items fetched successfully");
        return ConversionUtil.getCartItemModelList(cartItemEntityList);
    }

    public CartItem addCartItem(CartItem cartItem, Integer userId){
        log.info("Adding item to the cart");
        CartItemEntity cartItemEntity = ConversionUtil.getCartItemEntity(cartItem);
        System.out.println(userId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        log.info("user fetched");
        if(userEntity.isEmpty()){
            log.error("invalid user");
            throw new InvalidDataException("invalid user");
        }
        cartItemEntity.setUser(userEntity.get());
        Optional<MenuItemEntity> menuItem = menuItemRepository.findById(cartItem.getMenuItem().getItemId());
        log.info("item fetched");
        if(menuItem.isEmpty()){
            log.error("invalid item");
            throw new InvalidDataException("invalid menu Item");
        }
        cartItemEntity.setMenuItem(menuItem.get());
        log.info("ready to add to cart");
        cartItemEntity = cartItemRepository.save(cartItemEntity);
        cartItemRepository.flush();
        log.info("Item added successfully to cart with id {}", cartItemEntity.getCartItemId());
        return ConversionUtil.getCartItemModel(cartItemEntity);
    }

    public CartItem updateCartItem(CartItem cartItem){
        log.info("updating item to cart");
        Optional<CartItemEntity> optionalCartItem = cartItemRepository.findById(cartItem.getCartItemId());
        if(optionalCartItem.isPresent()){
            CartItemEntity cartItemEntity = optionalCartItem.get();
            cartItemEntity.setQuantity(cartItem.getQuantity());
            cartItemEntity.setSubtotal(cartItem.getSubtotal());
            cartItemRepository.flush();
            log.info("item {} update successfully",cartItemEntity.getCartItemId());
            return ConversionUtil.getCartItemModel(cartItemEntity);
        } else {
            throw new InvalidDataException("cart Item not found");
        }
    }

    public void removeCartItem(Integer cartItemId){
        if(Objects.isNull(cartItemId)){
            throw new InvalidDataException("Invalid cart item Id");
        }
        log.info("Removing item from Cart");
        cartItemRepository.deleteById(cartItemId);
        cartItemRepository.flush();
        log.info("Item removed from cart successfully");
    }
}
