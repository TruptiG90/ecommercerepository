package com.ecommerce.order.ecommerce.ecomemrceservice.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.UserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectUserNamePasswordException;

@FeignClient(url = "http://localhost:8787/user", value="user-service")
public interface UserInterface {
	
	 @RequestMapping(method = RequestMethod.POST , value=  "/user")
	  public String createUser(@RequestBody UserDTO userDto);
	  
		  
	 @RequestMapping(method = RequestMethod.GET,value="/getUser/{id}") 
	  public User getUserRecord( long id);
	  
	 @RequestMapping(method = RequestMethod.GET,value="/login") 
	  public boolean logInToBankApplication(String userName,String password) throws RuntimeException;
	
	

}
