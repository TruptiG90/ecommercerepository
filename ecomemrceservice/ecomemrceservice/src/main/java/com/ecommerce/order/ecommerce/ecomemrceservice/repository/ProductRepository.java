package com.ecommerce.order.ecommerce.ecomemrceservice.repository;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Product;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public Product findByproductNameContaining(String productName);

	public HashSet<Product>  findByproductTypeContaining(String productType);

}
