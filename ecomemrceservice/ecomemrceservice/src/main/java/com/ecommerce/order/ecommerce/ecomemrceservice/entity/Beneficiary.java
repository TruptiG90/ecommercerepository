package com.ecommerce.order.ecommerce.ecomemrceservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Beneficiary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long benId;
 
     

	@ManyToOne
	@JoinColumn(name="accountNo", nullable=false)
    private Account account;
   
	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}


	
	public long getBenId() {
		return benId;
	}
  
	
	public void setBenId(long benId) {
		this.benId = benId;
	}

	public Beneficiary() {
		super();
	}
	
}
