package com.ecommerce.order.ecommerce.ecomemrceservice.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.BenificiaryDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;

@FeignClient(url = "http://localhost:8788/bankapplication", value="bank-service")
public interface BankInterface {
	
	@RequestMapping(method = RequestMethod.POST, value="/benificiary")
	public ResponseEntity<Object> addBenificiary(BenificiaryDTO benificiaryDTO);
	
	
	@RequestMapping(method = RequestMethod.POST, value="/deposite")
	public User depositeToAccount(String userName, String password, long amount);
	

	@RequestMapping(method = RequestMethod.POST, value="/withdraw")
	public User withdrawFromAccount(String userName, String password, long amount);


	@RequestMapping(method = RequestMethod.POST, value="/fundTransfer")
	public User fundTransfer(long sender, String password, long reciver, long amount) ;
	

}
