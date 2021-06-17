package com.ecommerce.order.ecommerce.ecomemrceservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.BenificiaryDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Account;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Beneficiary;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectAmountException;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.UserNotFoundException;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.AccountRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.BankRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.BeneficiaryRepository;



@Service
public class BankService {

	@Autowired
	BankRepository bankRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	BeneficiaryRepository beneficiaryRepository;
	@Autowired
	UserService userService;

	public User depositeToAccount(String userName, String password, long amount) {

		if (userService.loginToApplication(userName, password)) {
			if (amount > 0) {

				User user = userService.getUserByUserName(userName);
				Account account = user.getAccount();
				long currentAccountBalance = account.getAccountBalance();
				long updatedBalance = currentAccountBalance + amount;
				account.setAccountBalance(updatedBalance);
				user.setAccount(account);
				return bankRepository.save(user);

			} else {
				throw new IncorrectAmountException("Incorrect Amout Provided");
			}
		} else {
			throw new UserNotFoundException("User Not Found");
		}

	}

	public User withdrawFromAccount(String userName, String password, long amount) {

		if (userService.loginToApplication(userName, password)) {
			if (amount > 0) {

				User user = userService.getUserByUserName(userName);
				Account account = user.getAccount();
				long currentAccountBalance = account.getAccountBalance();
				if (currentAccountBalance > 0) {
					long updatedBalance = currentAccountBalance - amount;
					account.setAccountBalance(updatedBalance);
					user.setAccount(account);
					return bankRepository.save(user);
				} else {
					throw new RuntimeException("No Sufficient Balance");
				}
			} else {
				throw new IncorrectAmountException("Amount should be greater than zero");
			}
		} else {
			throw new RuntimeException("User Not Found");
		}

	}

	public User fundTransferFromAccount(long sender, String password, long reciver, long amount) {
		User beneficiary = userService.getUserRecord(reciver);
		User user = userService.getUserRecord(sender);
		if (userService.loginToApplication(user.getUserName()  , password)) {
			if (amount > 0) {
				Account userAccountDetailOfSender = user.getAccount();
				Account userAccountDetailOfBeneficiary = beneficiary.getAccount();
				long currentAccountBalance = userAccountDetailOfSender.getAccountBalance();
				if (currentAccountBalance > 0) {
					System.out.println(" ---  currentAccountBalance " + currentAccountBalance);

					long updatedBalance = currentAccountBalance - amount;
					userAccountDetailOfSender.setAccountBalance(updatedBalance);
					long currentBalanceOfBeneficiary = userAccountDetailOfBeneficiary.getAccountBalance();
					long updatedBalanceOfBeneficiary = currentBalanceOfBeneficiary + amount;
					userAccountDetailOfBeneficiary.setAccountBalance(updatedBalanceOfBeneficiary);
					user.setAccount(userAccountDetailOfSender);
					beneficiary.setAccount(userAccountDetailOfBeneficiary);
					bankRepository.save(beneficiary);
					return bankRepository.save(user);
					// return new
					// ResponseEntity<>("Amount transferred successfully !!",HttpStatus.OK);

				} else {
					throw new RuntimeException("No Sufficient Balance");
				}
			} else {
				throw new IncorrectAmountException("Amount should be greater than zero");
			}
		} else {
			throw new RuntimeException("User Not Found");
		}

	}

	public User getAccountRecord(long id) {
		Optional<User> result = bankRepository.findById(id);
		User user = null;
		if (result.isPresent()) {
			System.out.println(" ---  result " + result);
			user = result.get();
			return user;
		} else {

			throw new UserNotFoundException("User Not Found");
		}
	}

	public ResponseEntity<Object> addBenificiary(BenificiaryDTO benificiaryDTO) {
		Optional<Account> holder = accountRepository.findById(benificiaryDTO.getPrimaryId());
		Optional<Account> beneficiaryAccount = accountRepository.findById(benificiaryDTO.getSecondaryId());
		if(holder.isPresent() && beneficiaryAccount.isPresent()) {
			Account  sender = holder.get();
			Account reciver = beneficiaryAccount.get();
		Beneficiary b = new Beneficiary();
		b.setAccount(reciver);
		List<Beneficiary> beneficiary = new ArrayList();
		beneficiary.add(b);
		sender.setBeneficiaries(beneficiary);
		accountRepository.save(sender);
		}
		return new ResponseEntity("Beneficiary added scuccessfull", HttpStatus.OK);
	}
	/*
	 * public ResponseEntity<Object> addBenificiary(BenificiaryDTO benificiaryDTO) {
	 * Optional<Account> accountPrimary =
	 * accountRepository.findById(benificiaryDTO.getPrimaryId()); Optional<Account>
	 * accountSecondary =
	 * accountRepository.findById(benificiaryDTO.getSecondaryId()); Beneficiary b =
	 * new Beneficiary(); if(accountPrimary.isPresent() &&
	 * accountSecondary.isPresent()) {
	 * 
	 * b.setAccount_id(accountSecondary.get().getAccountNo());
	 * Optional<List<Beneficiary>> benficiary =
	 * Optional.ofNullable(accountPrimary.get().getBeneficiaries());
	 * if(benficiary.isPresent()) { benficiary.get().add(b);
	 * benficiary.get().stream().forEach(beneficiaryRepository::save);
	 * accountPrimary.get().setBeneficiaries(benficiary.get()); }else {
	 * List<Beneficiary> benficiaryList = new ArrayList(); benficiaryList.add(b);
	 * benficiaryList.stream().forEach(beneficiaryRepository::save);
	 * accountPrimary.get().setBeneficiaries(benficiaryList); }
	 * 
	 * accountRepository.save(accountPrimary.get());
	 * 
	 * return new ResponseEntity("Beneficiary added scuccessfull",HttpStatus.OK); }
	 * else { throw new IncorrectAmountException("Incorrect Account provided"); }
	 */

}
