package com.ecommerce.order.ecommerce.ecomemrceservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.AccountDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.EcommerceUserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.UserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Account;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.EcommerceUser;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Wallet;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectUserNamePasswordException;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.UnableToCreateUserRegistrationExeption;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.UserNotFoundException;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.AccountRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.BankRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.EcommerceUserRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.WalletRepository;


@Service
public class EcommerceUserService {

	
	@Autowired
	EcommerceUserRepository ecommerceUserRepository;
	
	@Autowired
     WalletRepository walletRepository;
	
	public String createUserRecord(EcommerceUserDTO ecommecerUserDto) {
		
		try {
			
		Wallet wallet  =  new Wallet();
		wallet.setWalletBalance(10);
		walletRepository.save(wallet);
		EcommerceUser ecommecerUser =convertDtoToUser(ecommecerUserDto);
		ecommecerUser.setWallet(wallet);
		ecommerceUserRepository.save(ecommecerUser);
	 	return  new String( "You have successfully registered yourself to ecommece application") ;	
		}
		catch(UnableToCreateUserRegistrationExeption unableToCreateUserRegistrationExeption){
			throw new UnableToCreateUserRegistrationExeption("Unable to create user");
		}
		
}

	public boolean loginToApplication(String userName, String password)  throws RuntimeException{
		EcommerceUser userResult = getUserByUserName(userName);

		if (userResult.getUserName().equals(userName) && userResult.getPassword().equals(password)) {

			return true; 
		} else {

			throw new IncorrectUserNamePasswordException("Incorrect Username/Password Provided");
		}
	}
  
	
	

	public EcommerceUser getUserByUserName(String userName) {
	 if(userName!= null && !userName.equals("") && userName.length()> 0 ) {
	
	     return ecommerceUserRepository.findByUserNameContaining(userName);
		 }
	
		  else { 
			  throw new UserNotFoundException("User Not Found"); }
		
	}


	public EcommerceUser getUserRecord(long id) {
		
		Optional<EcommerceUser> result = ecommerceUserRepository.findById(id);
		if (result.isPresent()) {
			EcommerceUser user = result.get();
			
			return user;
		} else {

			throw new UserNotFoundException("User Not Found");
		}
	}
	
	
	 public UserDTO convertUserDTO(User user) {
		 UserDTO dto = new UserDTO();
		      dto.setEmailId(user.geteMail());
		      dto.setFirstName(user.getFirstName());
		      dto.setLastName(user.getLastName());
		      dto.setPassword(user.getPassword());
		      dto.setPhoneNo(user.getPhoneNo());
		      dto.setUserName(user.getUserName());
		    return dto;
		 }
	

	public EcommerceUser convertDtoToUser(EcommerceUserDTO ecommecerUserDto) {

		EcommerceUser ecommerceUser = new EcommerceUser();
		ecommerceUser.setPassword(ecommecerUserDto.getPassword());
		ecommerceUser.setPhoneNo(ecommecerUserDto.getPhoneNo());
		ecommerceUser.setUserName(ecommecerUserDto.getUserName());
		 System.out.println("...... "+ecommerceUser);
		  return ecommerceUser;
	}


}