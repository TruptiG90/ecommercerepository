package com.ecommerce.order.ecommerce.ecomemrceservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {



	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO )
	 private Long id;

	   @ManyToOne
	   private Product product;

	   @Column
	   private int quantity;

 
      @Column
	   private long totalPrize;
	   
	   
		 public void setTotalPrize(long totalPrize) {
		this.totalPrize = totalPrize;
	}





		public Long getId() {
				return id;
			}

			public void setId(Long id) {
				this.id = id;
			}

			public CartItem() {
				super();
				// TODO Auto-generated constructor stub
			}

			public Product getProduct() {
				return product;
			}

			public void setProduct(Product product) {
				this.product = product;
			}

			public int getQuantity() {
				return quantity;
			}

			public void setQuantity(int quantity) {
				this.quantity = quantity;
			}
}
