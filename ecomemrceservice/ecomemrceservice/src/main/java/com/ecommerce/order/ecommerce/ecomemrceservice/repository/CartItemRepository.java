package com.ecommerce.order.ecommerce.ecomemrceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.ecommerce.ecomemrceservice.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
