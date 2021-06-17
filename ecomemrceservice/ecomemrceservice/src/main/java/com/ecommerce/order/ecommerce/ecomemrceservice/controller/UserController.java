package com.ecommerce.order.ecommerce.ecomemrceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.UserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectUserNamePasswordException;
import com.ecommerce.order.ecommerce.ecomemrceservice.service.UserService;


@RestController
@RequestMapping("/application")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	 @PostMapping("/user") 
	  public String createUser(@RequestBody UserDTO userDto)
	  { 
		 System.out.println("userDto "+userDto.getEmailId());
		 User user =   userService.convertDtoToUser(userDto);
		  return userService.createUserRecord(user) ;
	  
	  }
		  
	  @GetMapping("/getUser/{id}") 
	  public User getUserRecord( long id)
	  { 
		  return userService.getUserRecord(id);
	  
	  }
	   
	  @GetMapping("/login") 
	  public boolean logInToBankApplication(String userName,String password) throws RuntimeException { 
		  if(password!= null && !password.equals("") && password.length()> 0 && userName!= null && !userName.equals("") && userName.length()> 0 ) 
				
		  return userService.loginToApplication(userName,password);
		  else
			 
			throw new IncorrectUserNamePasswordException("Incorrect Username/Password Provided");
	  }
}
