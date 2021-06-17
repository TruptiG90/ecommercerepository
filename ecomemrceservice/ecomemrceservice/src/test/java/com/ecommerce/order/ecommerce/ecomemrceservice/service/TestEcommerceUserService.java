package com.ecommerce.order.ecommerce.ecomemrceservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.order.ecommerce.ecomemrceservice.controller.EcommerceUserController;
import com.ecommerce.order.ecommerce.ecomemrceservice.controller.UserController;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.EcommerceUserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.UserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Account;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Beneficiary;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.EcommerceUser;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Wallet;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectUserNamePasswordException;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.UnableToCreateUserRegistrationExeption;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.AccountRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.BankRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.BeneficiaryRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.EcommerceUserRepository;

@ExtendWith(MockitoExtension.class)
public class TestEcommerceUserService {


		@Mock
		EcommerceUserService ecommerceUserService  = new EcommerceUserService();
		
		@Mock
		EcommerceUserRepository ecommerceUserRepository;

		@Mock
		AccountRepository accountRepository;
		
		@Mock
		BeneficiaryRepository beneficiaryRepository;
		
		@Spy
		@InjectMocks
		EcommerceUserController ecommerceUserController;
		
		
		
		static EcommerceUser ecommerceUser;
		
		
		static EcommerceUserDTO ecommerceUserDTO;
		
		
		
		@BeforeAll
		public static void init(){
		  
		
			
			ecommerceUser = new EcommerceUser();
			ecommerceUser.setPassword("password");
			ecommerceUser.setPhoneNo(12345);
			ecommerceUser.setUserName("tg");
			
			
			ecommerceUserDTO = new EcommerceUserDTO();
			ecommerceUserDTO.setPassword("password");
			ecommerceUserDTO.setPhoneNo(12345);
			ecommerceUserDTO.setUserName("tg");
			
			
		
		}
		
		
		  @Test
		  @DisplayName("User registration test") 
		  public void testUserCreation() {
		  when(ecommerceUserService.createUserRecord(ecommerceUserDTO)).
		  thenReturn( new String( "You have successfully registered yourself to ecommece application")); 
		  String result = ecommerceUserService.createUserRecord(ecommerceUserDTO);
		  verify(ecommerceUserService).createUserRecord(ecommerceUserDTO); 
		  assertEquals( new String( "You have successfully registered yourself to ecommece application"),result); 
		  
		  }
	 
		  @Test
		  @DisplayName("Test for save method for Ecommerce User entity ")
		  public boolean testUserSave() {
		 
			  EcommerceUser testUser = new EcommerceUser();
			  testUser.setPassword("password");
			  testUser.setPhoneNo(12345);
			  testUser.setUserName("user");
			  
			  ecommerceUserRepository.save(testUser);
			  return true;
		  }
			
		  @Test
		  @DisplayName("Test for save method for Account,Beneficiary,Wallet entity ")
		  public boolean testWalletAccountBeneficiarySave() {
		 
			  Account account  = new Account();
			  account.setAccountBalance(1000);
			  account.setAccountNo(1L);
			  account.setAcountType("Savings");
			  Beneficiary  b = new Beneficiary();
			  b.setAccount(account);
			  b.setBenId(1L);
			  List<Beneficiary> list = new ArrayList<Beneficiary>();
			  list.add(b);
			  account.setBeneficiaries(list);
			  beneficiaryRepository.save(b);
			  accountRepository.save(account);
			  
			  Wallet wallet = new Wallet();
			  wallet.setWalletBalance(1000);
			  wallet.setWalletId(1L);
			  accountRepository.save(account);
			  return true;
		  } 
			/*
			 * @Test
			 * 
			 * @DisplayName("User registration negative test") public void
			 * testUserCreationNegative() { when(userService.createUserRecord(invalidUser)).
			 * thenThrow(UnableToCreateUserRegistrationExeption.class);
			 * assertThrows(UnableToCreateUserRegistrationExeption.class,() ->
			 * userService.createUserRecord(invalidUser)); }
			 */
			  
			  	  
			  @Test
		     @DisplayName("User login test positive test") 
			  public void testUserLogin() {
			  when(ecommerceUserService.loginToApplication(ecommerceUser.getUserName(),ecommerceUser.getPassword())).
			  thenReturn(true); 
			  boolean isLoggedIn =
					  ecommerceUserController.logInToApplication(ecommerceUser.getUserName(),ecommerceUser.getPassword());
			  verify(ecommerceUserService).loginToApplication(ecommerceUser.getUserName(),ecommerceUser.getPassword())
			  ; assertEquals(true,isLoggedIn); }
			  
			  @Test
			  @DisplayName("User login test negative test") public void
			  testUserLoginWithNegative() {
			  when(ecommerceUserService.loginToApplication(ecommerceUser.getUserName(),"wrongpassword"
			)).thenThrow(IncorrectUserNamePasswordException.class);
			  assertThrows(IncorrectUserNamePasswordException.class,() ->
			  ecommerceUserController.logInToApplication(ecommerceUser.getUserName(),"wrongpassword"
			 )); }
			 
			  @Test
			  @DisplayName("Test fetch user by his username") 
			  public void testUserByHisUserName() {
			  when(ecommerceUserService.getUserByUserName("tg")).thenReturn(ecommerceUser);
			  EcommerceUser result = ecommerceUserService.getUserByUserName("tg");
			  verify(ecommerceUserService).getUserByUserName("tg");
			  assertEquals(ecommerceUser,result);
			  }
			  
			  
				
			  @Test
			  @DisplayName("get user record by id")
			  public void testGetUserRecordById() {
				  
				  when(ecommerceUserService.getUserRecord(1L)).thenReturn(ecommerceUser);
				  
				  EcommerceUser userResult = ecommerceUserController.getUserRecord(1L);
				  
				  verify(ecommerceUserService).getUserRecord(1L);
				  
				  assertEquals(ecommerceUser,userResult);
				  
			  }
		  
	}
