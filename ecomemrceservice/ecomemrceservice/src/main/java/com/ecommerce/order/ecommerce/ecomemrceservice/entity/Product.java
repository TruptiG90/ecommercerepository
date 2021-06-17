package com.ecommerce.order.ecommerce.ecomemrceservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
public class Product {
     
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO )
	long productId;
	 	 
	 public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "product_name")
	String productName;
	 
	 
	@Column(name = "product_type")
	String productType;
	 
	@Column(name = "product_price")
	long productPrice;
	 
	 

	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}

	public long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}
	

}
