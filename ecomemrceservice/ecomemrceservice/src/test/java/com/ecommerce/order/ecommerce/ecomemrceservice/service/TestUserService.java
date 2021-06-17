package com.ecommerce.order.ecommerce.ecomemrceservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecommerce.order.ecommerce.ecomemrceservice.controller.UserController;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.UserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Account;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectUserNamePasswordException;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.UnableToCreateUserRegistrationExeption;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.AccountRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.BankRepository;



@ExtendWith(MockitoExtension.class)
public class TestUserService {

	@Mock
	UserService userService  = new UserService();
	
	@Mock
	BankRepository bankRepository;

	@Mock
	AccountRepository accountRepository;
	
	@Spy
	@InjectMocks
	UserController userController;
	
	
	
	static User user;
	static User invalidUser;
	static UserDTO userDTO;
	static UserDTO invalidUserDTO;
	static String returnValue ;
	
	static Account account;
	
	
	@BeforeAll
	public static void init(){
	  
		user  =new User();
		user.seteMail("a@mail.com");
		user.setFirstName("a");
		user.setLastName("z");
		user.setUserName("az");
		user.setPassword("password");
		user.setPhoneNo(1);
		
		account = new Account();
		account.setAccountBalance(1000);
		account.setAcountType("savings");
	
		
		invalidUser  =new User();
		invalidUser.seteMail("a"); // invalid mail
		invalidUser.setFirstName("a");
		invalidUser.setLastName("z");
		invalidUser.setUserName("az");
		invalidUser.setPassword("password");
		invalidUser.setPhoneNo(1);
		
		
		userDTO = new UserDTO();
		userDTO.setEmailId("tg@mail.com");
		userDTO.setFirstName("t");
		userDTO.setLastName("t");
		userDTO.setPassword("password");
		userDTO.setPhoneNo(12345);
		userDTO.setUserName("tg");
		
		
	
	}
	
	
	  @Test
	  @DisplayName("User registration test") 
	  public void testUserCreation() {
	  when(userService.createUserRecord(user)).
	  thenReturn( new String( "You have successfully registered yourself")); 
	  String result = userService.createUserRecord(user);
	  verify(userService).createUserRecord(user); 
	  assertEquals( new String( "You have successfully registered yourself"),result); 
	  
	  }
 
	  @Test
	  @DisplayName("Test for save method for User entity ")
	  public boolean testUserSave() {
	 
		  User testUser = new User();
		  testUser.seteMail("user@mail.com");
		  testUser.setFirstName("userFirstName");
		  testUser.setLastName("userLastName");
		  testUser.setPassword("password");
		  testUser.setPhoneNo(12345);
		  testUser.setUserName("user");
		  
		  bankRepository.save(testUser);
		  return true;
	  }
		
	  @Test
	  @DisplayName("Test for save method for Account entity ")
	  public boolean testAccountSave() {
	 
		  Account account = new Account();
		  account.setAccountBalance(1000);
		  account.setAccountNo(1L);
		  account.setAcountType("Savings");
		  accountRepository.save(account);
		  return true;
	  } 
		  @Test
		  @DisplayName("User registration negative test")
		  public void  testUserCreationNegative() { 
		  when(userService.createUserRecord(invalidUser)).
		  thenThrow(UnableToCreateUserRegistrationExeption.class);
		  assertThrows(UnableToCreateUserRegistrationExeption.class,() ->
		  userService.createUserRecord(invalidUser)); 
		  }
		  
		  	  
		  @Test
	     @DisplayName("User login test positive test") 
		  public void testUserLogin() {
		  when(userService.loginToApplication(user.getUserName(),user.getPassword())).
		  thenReturn(true); 
		  boolean isLoggedIn =
		  userController.logInToBankApplication(user.getUserName(),user.getPassword());
		  verify(userService).loginToApplication(user.getUserName(),user.getPassword())
		  ; assertEquals(true,isLoggedIn); }
		  
		  @Test
		  @DisplayName("User login test negative test") public void
		  testUserLoginWithNegative() {
		  when(userService.loginToApplication(invalidUser.getUserName(),invalidUser.
		  getPassword())).thenThrow(IncorrectUserNamePasswordException.class);
		  assertThrows(IncorrectUserNamePasswordException.class,() ->
		  userController.logInToBankApplication(invalidUser.getUserName(),invalidUser.
		  getPassword())); }
		 
		  @Test
		  @DisplayName("Test fetch user by his username") 
		  public void testUserByHisUserName() {
		  when(userService.getUserByUserName("User1")).thenReturn(user);
		  User result = userService.getUserByUserName("User1");
		  verify(userService).getUserByUserName("User1");
		  assertEquals(user,result);
		  }
		  
		  @Test
		  @DisplayName("Test repository method ::  findByUserNameContaining") 
		  public void testUserByUsernameContaining() {
			  
			  BankRepository bankRepository = Mockito.mock(BankRepository.class);
			  
			  Mockito.when(bankRepository.findByUserNameContaining("az")).thenReturn(user);
			  
			  User userSearched = bankRepository.findByUserNameContaining("az");
			  
			  Mockito.verify(bankRepository).findByUserNameContaining("az");
		      
			  assertEquals(user, userSearched);
		  
		  }
			
		  @Test
		  @DisplayName("get user record by id")
		  public void testGetUserRecordById() {
			  
			  when(userService.getUserRecord(1L)).thenReturn(user);
			  
			  User userResult = userController.getUserRecord(1L);
			  
			  verify(userService).getUserRecord(1L);
			  
			  assertEquals(user,userResult);
			  
		  }
	  
}
