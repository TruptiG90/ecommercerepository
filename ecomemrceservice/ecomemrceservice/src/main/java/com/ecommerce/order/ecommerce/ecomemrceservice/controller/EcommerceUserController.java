package com.ecommerce.order.ecommerce.ecomemrceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.EcommerceUserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.UserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.EcommerceUser;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectUserNamePasswordException;
import com.ecommerce.order.ecommerce.ecomemrceservice.service.EcommerceUserService;
import com.ecommerce.order.ecommerce.ecomemrceservice.service.UserService;


@RestController
@RequestMapping("/ecommerceapplication")
public class EcommerceUserController {
	
	
	@Autowired
	EcommerceUserService ecommeceUserService;
	
	 @PostMapping("/euser") 
	  public String createUser(@RequestBody EcommerceUserDTO ecommecerUserDto)
	  { 

		 EcommerceUser ecommerceUser =   ecommeceUserService.convertDtoToUser(ecommecerUserDto);
		  return ecommeceUserService.createUserRecord(ecommecerUserDto) ;
	  
	  }
		  
	  @GetMapping("/getEcommeceUser/{id}") 
	  public EcommerceUser getUserRecord( long id)
	  { 
		  return ecommeceUserService.getUserRecord(id);
	  
	  }
	   
	  @GetMapping("/logintoecommeceapp") 
	  public boolean logInToApplication(String userName,String password) throws RuntimeException { 
		  if(password!= null && !password.equals("") && password.length()> 0 && userName!= null && !userName.equals("") && userName.length()> 0 ) 
				
		  return ecommeceUserService.loginToApplication(userName,password);
		  else
			 
			throw new IncorrectUserNamePasswordException("Incorrect Username/Password Provided");
	  }
	  
	  
}
