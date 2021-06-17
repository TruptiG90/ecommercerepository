package com.ecommerce.order.ecommerce.ecomemrceservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Account {

	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
	long accountNo;
       
     
    @Column
    @NotNull
	String acountType;
    
    @Column
    @NotNull
	long accountBalance;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    List<Beneficiary> beneficiaries ;
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getAcountType() {
		return acountType;
	}

	public void setAcountType(String acountType) {
		this.acountType = acountType;
	}

	public long getAccountBalance() {
		return accountBalance;
	}
  public List<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	} 
}	
    