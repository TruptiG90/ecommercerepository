package com.ecommerce.order.ecommerce.ecomemrceservice.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OrderCart {

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;

	@Column
	private Date createdOn;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId") 
	private EcommerceUser createdBy;
	 
	@Column
	private String orderStatus;
	
    
	
	@OneToMany
	   private Set<CartItem> cartItems;
	
	

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	 public OrderCart() {
			super();
			// TODO Auto-generated constructor stub
		}

	public EcommerceUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(EcommerceUser createdBy) {
		this.createdBy = createdBy;
	}
	
}
