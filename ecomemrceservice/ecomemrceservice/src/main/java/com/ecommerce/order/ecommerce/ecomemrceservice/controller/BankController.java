package com.ecommerce.order.ecommerce.ecomemrceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.BenificiaryDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.service.BankService;



@RestController
@RequestMapping("/bankapplication")
public class BankController {

	@Autowired
	BankService bankService;
	
	@PostMapping("/benificiary")
	public ResponseEntity<Object> addBenificiary(BenificiaryDTO benificiaryDTO) {
		return bankService.addBenificiary(benificiaryDTO);
	}
	
	@PostMapping("/deposite")
	public User depositeToAccount(String userName, String password, long amount) {
		return bankService.depositeToAccount(userName, password, amount);
	}

	@PostMapping("/withdraw")
	public User withdrawFromAccount(String userName, String password, long amount) {
		return bankService.withdrawFromAccount(userName, password, amount);
	}

	@PostMapping("/fundTransfer")
	public User fundTransfer(long sender, String password, long reciver, long amount) {
		return bankService.fundTransferFromAccount(sender, password, reciver, amount);
	}

}
