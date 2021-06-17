package com.ecommerce.order.ecommerce.ecomemrceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.ecommerce.ecomemrceservice.entity.EcommerceUser;

@Repository
public interface EcommerceUserRepository extends JpaRepository<EcommerceUser, Long> {

	public EcommerceUser findByUserNameContaining(String userName);

}
