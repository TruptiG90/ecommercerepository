package com.ecommerce.order.ecommerce.ecomemrceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
