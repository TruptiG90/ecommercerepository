package com.ecommerce.order.ecommerce.ecomemrceservice.dto;

public class OrderDTO {

	  private String productName;
	  private int productQuantity;
	  private Long userId;
	  boolean payFromWallet;
     
	  

	public boolean isPayFromWallet() {
		return payFromWallet;
	}
	public void setPayFromWallet(boolean payFromWallet) {
		this.payFromWallet = payFromWallet;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		
		public int getProductQuantity() {
			return productQuantity;
		}
		public void setProductQuantity(int productQuantity) {
			this.productQuantity = productQuantity;
		}
	
		
}

