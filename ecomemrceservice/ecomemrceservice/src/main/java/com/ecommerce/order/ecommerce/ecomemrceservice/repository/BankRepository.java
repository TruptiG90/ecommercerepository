package com.ecommerce.order.ecommerce.ecomemrceservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;



@Repository
public interface BankRepository extends JpaRepository<User, Long> {

	public User findByUserNameContaining(String userName);

}
