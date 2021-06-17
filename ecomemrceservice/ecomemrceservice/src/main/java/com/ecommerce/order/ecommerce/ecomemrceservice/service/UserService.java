package com.ecommerce.order.ecommerce.ecomemrceservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.AccountDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.UserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Account;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectUserNamePasswordException;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.UnableToCreateUserRegistrationExeption;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.UserNotFoundException;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.AccountRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.BankRepository;


@Service
public class UserService {

	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public String createUserRecord(User user) {
		
		try {
		AccountDTO uerAccountDetailDTO  =  new AccountDTO();
		uerAccountDetailDTO.setAccountBalance(1000);
		uerAccountDetailDTO.setAcountType("Savings");
		user.setAccount(convertDtoToUserAccountDetails(uerAccountDetailDTO));
		User result = bankRepository.save(user);
		accountRepository.save(result.getAccount());
	 	return  new String( "You have successfully registered yourself") ;	
		}
		catch(UnableToCreateUserRegistrationExeption unableToCreateUserRegistrationExeption){
			throw new UnableToCreateUserRegistrationExeption("Unable to create user");
		}
		
}

	public boolean loginToApplication(String userName, String password)  throws RuntimeException{
	User userResult = getUserByUserName(userName);
	/*
	 * boolean isUserPresent = userResult.getUserId() != null ? true : false;
	 * if(isUserPresent) {
	 */
		if (userResult.getUserName().equals(userName) && userResult.getPassword().equals(password)) {

			return true; 
		} else {

			throw new IncorrectUserNamePasswordException("Incorrect Username/Password Provided");
		}
	}

	/*
	 * else {
	 * 
	 * throw new
	 * IncorrectUserNamePasswordException("Incorrect Username/Password Provided"); }
	}
	 */
	
	

	public User getUserByUserName(String userName) {
	 if(userName!= null && !userName.equals("") && userName.length()> 0 ) {
	
	     return bankRepository.findByUserNameContaining(userName);
		 }
	
		  else { 
			  throw new UserNotFoundException("User Not Found"); }
		
	}


	public User getUserRecord(long id) {
		
		Optional<User> result = bankRepository.findById(id);
		if (result.isPresent()) {
			User user = result.get();
			
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
	 public User convertDtoToUser(UserDTO userDto){
		 System.out.println("..userDto  1... "+userDto.getEmailId());
		 User user = new User();
		 user.seteMail(userDto.getEmailId());
		 user.setFirstName(userDto.getFirstName());
		 user.setLastName(userDto.getLastName());
		 user.setPassword(userDto.getPassword());
		 user.setPhoneNo(userDto.getPhoneNo());
		 user.setUserName(userDto.getUserName());
		 System.out.println("...... "+user);
		  return user;
		 }
	 public Account convertDtoToUserAccountDetails(AccountDTO accountDTO){
		 Account userAccountDetail = new Account();
		 userAccountDetail.setAccountBalance(accountDTO.getAccountBalance());
		 userAccountDetail.setAcountType(accountDTO.getAcountType());
		 return userAccountDetail;
		 }
}
