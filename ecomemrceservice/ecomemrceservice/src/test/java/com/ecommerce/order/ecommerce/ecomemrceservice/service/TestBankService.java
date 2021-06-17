package com.ecommerce.order.ecommerce.ecomemrceservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.order.ecommerce.ecomemrceservice.controller.BankController;
import com.ecommerce.order.ecommerce.ecomemrceservice.controller.UserController;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.BenificiaryDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Account;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.AccountRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.BankRepository;



public class TestBankService {
	
	
	@Mock
	BankService bankService = new BankService();
	
	@Mock
	static
	UserService userService;
	
	@Spy
	@InjectMocks
	BankController bankController;
	
	@Spy
	@InjectMocks
	UserController userController;

	@InjectMocks
	AccountRepository accountRepository;
	

	
	@InjectMocks
	BankRepository bankRepository;
	
	static BenificiaryDTO benificiaryDTO;
	static User sender;
	static Optional<User> reciver;
	static Optional<Account> senderAccount;
	static  Optional<Account> reciverAccount;
	
	@BeforeAll
	public static void setup() {
		
		userService = new UserService();
		sender  =new User();
		sender.seteMail("a@mail.com");
		sender.setFirstName("a");
		sender.setLastName("z");
		sender.setUserName("az");
		sender.setPassword("password");
		sender.setPhoneNo(1);
		sender.setUserId(1L);
		/*senderAccount = new Account();
		senderAccount.setAccountBalance(1000);
		senderAccount.setAccountNo(1L);
		senderAccount.setAcountType("Savings");
	    sender.setAccount(senderAccount);*/
		
		
/*		
		reciver  =new User();
		reciver.seteMail("b@mail.com");
		reciver.setFirstName("b");
		reciver.setLastName("z");
		reciver.setUserName("b");
		reciver.setPassword("password");
		reciver.setPhoneNo(1);
		reciver.setUserId(2L);
		reciverAccount = new Account();
		reciverAccount.setAccountBalance(1000);
		reciverAccount.setAccountNo(2L);
		reciverAccount.setAcountType("Savings");
		reciver.setAccount(reciverAccount);*/
	
		
		/*benificiaryDTO= new BenificiaryDTO();
		benificiaryDTO.setPrimaryId(sender.getAccount().getAccountNo());
		benificiaryDTO.setSecondaryId(reciver.getAccount().getAccountNo());*/
	}
	
	@Test
	@DisplayName("Test get Account Repository Method")
	public void testAccountRepositoryMethod()
	{
	
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		Mockito.when(accountRepository.findById(1L)).thenReturn(senderAccount);
	    Optional<Account> accountHolder = accountRepository.findById(1L);
	    Mockito.verify(accountRepository).findById(1L);
        assertEquals(senderAccount, accountHolder);
    
	}
	
	
	
	  @Test
	  @DisplayName("Test get User record") 
	  public void testUserGetRecord() {
	  BankRepository bankRepository =
	  Mockito.mock(BankRepository.class);
	  Mockito.when(bankRepository.findById(2L)).thenReturn(reciver);
	  Optional<User>
	  user = bankRepository.findById(2L);
	  Mockito.verify(bankRepository).findById(2L);
	  assertEquals(reciver,user);
	  }
	 
	 
	
		/*
		 * @Test
		 * 
		 * @DisplayName("test withdraw method") public void testAmoutWithdraw() {
		 * 
		 * //when(bankService.withdrawFromAccount(sender.getUserName(),sender.
		 * getPassword(), 10)).thenReturn(sender);
		 * 
		 * when(userService.loginToApplication(sender.getUserName(),sender.getPassword()
		 * )).thenReturn(true);
		 * 
		 * User testUser =
		 * bankController.withdrawFromAccount(sender.getUserName(),sender.getPassword(),
		 * 10);
		 * 
		 * verify(bankService).withdrawFromAccount(sender.getUserName(),
		 * sender.getPassword(), 10);
		 * 
		 * assertEquals(sender,testUser);
		 * 
		 * 
		 * }
		 */
	
	/*  @Test
	  @DisplayName("Test fund Transfer") 
	  public void testFundTransfer() {
	  
	  when(bankService. fundTransferFromAccount(1L, "password",
	  2L,10)).thenReturn(sender);
	  
	  User user = bankController.fundTransfer(1L,"password",2L,10) ;
	  
	  Mockito.verify(bankService.fundTransferFromAccount(1L,"password",2L,10));
	  
	  assertEquals(sender,user);
	  
	  
	  }*/
	 
	

}
