package com.ecommerce.order.ecommerce.ecomemrceservice.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.order.ecommerce.ecomemrceservice.entity.OrderCart;

public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {

	
	/*
	 * public Set<OrderCart> findByCreatedOnGreaterThanEqual(Date startDate);
	 * 
	 * public Set<OrderCart> findByCreatedOnLessThanEqual(Date endDate);
	 */
		
			public Set<OrderCart> findByCreatedOnBetween(Date fromDate, Date toDate);

}
