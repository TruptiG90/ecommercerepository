package com.ecommerce.order.ecommerce.ecomemrceservice.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.EcommerceUserDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.EcommerceUser;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectUserNamePasswordException;

@FeignClient(url = "http://localhost:8799/ecommerceapplication", value="ecommerceuser-service")
public interface EcommerceUserInterface {

	
	  @RequestMapping(method = RequestMethod.POST,value ="/euser") 
	  public String createUser(@RequestBody EcommerceUserDTO ecommecerUserDto);
	 
		  
	@RequestMapping(method = RequestMethod.GET,value ="/getEcommeceUser/{id}") 
	  public EcommerceUser getUserRecord( long id);
	 
	   
	@RequestMapping(method = RequestMethod.GET,value="/logintoecommeceapp") 
	  public boolean logInToBankApplication(String userName,String password) throws RuntimeException ;
	
}
