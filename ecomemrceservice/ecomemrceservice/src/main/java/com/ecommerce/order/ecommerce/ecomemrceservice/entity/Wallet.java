package com.ecommerce.order.ecommerce.ecomemrceservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Wallet {

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO )
	private long WalletId;  
	
	@Column
	private long walletBalance;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="account_no") 
	private Account linkedAccount;
	
	public Account getLinkedAccount() {
		return linkedAccount;
	}
	public void setLinkedAccount(Account linkedAccount) {
		this.linkedAccount = linkedAccount;
	}
	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getWalletId() {
		return WalletId;
	}
	public void setWalletId(long walletId) {
		WalletId = walletId;
	}
	
	public long getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(long walletBalance) {
		this.walletBalance = walletBalance;
	}
	
	
	  
}
