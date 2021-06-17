package com.ecommerce.order.ecommerce.ecomemrceservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.websocket.OnMessage;

import org.hibernate.validator.constraints.Range;

@Entity
public class User {
	
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		Long userId;
		
		
		@Column
		@Size(min=4 ,max=30, message="Username should be greater than 4 char and less than 30 char" )
		String userName;
		
		@Column
		@Size(min=4 ,max=8 ,  message="Password should be minimum of 8 char" )
		String password;
		
		@Column
		@NotNull(message ="First Name should be provided")
		String firstName;
		
		
		@Column
		@NotNull(message = "Last Name should be provided")
		String lastName;
		
		@Column
		@Email(message = "Email should be valid")
		String eMail;
		
		@Column
		long phoneNo;
		
		public long getUserId() {
			return userId;
		}


		public void setUserId(Long userId) {
			this.userId = userId;
		}


		public String getUserName() {
			return userName;
		}


		public void setUserName(String userName) {
			this.userName = userName;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public String geteMail() {
			return eMail;
		}


		public void seteMail(String eMail) {
			this.eMail = eMail;
		}


		public long getPhoneNo() {
			return phoneNo;
		}


	

		public void setPhoneNo(long phoneNo) {
			this.phoneNo = phoneNo;
		}

		
		  @OneToOne(cascade = CascadeType.ALL)
	 	  @JoinColumn(name="account_id") 
		  private Account account;
		 
		
		public Account getAccount() {
			return account;
		}


		public void setAccount(Account account) {
			this.account = account;
		}


		
		public User() {
			super();
		
		}
		
		
		

}
