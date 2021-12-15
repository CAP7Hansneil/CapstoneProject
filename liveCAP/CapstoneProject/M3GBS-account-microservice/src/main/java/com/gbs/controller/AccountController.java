package com.gbs.controller;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbs.entity.Account;
import com.gbs.entity.AccountResponse;
import com.gbs.repository.AccountRepository;




@RestController
@RequestMapping("/api")
public class AccountController {


@Autowired
AccountRepository accountRepository;
	
	//displaying all accounts
		@GetMapping("/account")
		public List<Account> getAllAccount() {
			List<Account> accountsList = accountRepository.findAll();
			System.out.println("Account Details : " + accountsList);
			return accountsList;
		}

		@GetMapping("/accountsResponse")
		public AccountResponse getAllAccountsResponse() {
			List<Account> accountsList = accountRepository.findAll();
			AccountResponse accountsResponse = new AccountResponse();
			accountsResponse.setAccountList(accountsList);
			return accountsResponse;
		}
		
		//READ THE ACCOUNT DATABASE AND GET THE COLLECTION IN ACCOUNTS TABLE
		@GetMapping("/account/response")
		public AccountResponse getAllFromAccountResponse() {
			List<Account> fromAccountList = accountRepository.findAll();
			AccountResponse fromAccountResponse = new AccountResponse();
			fromAccountResponse.setAccountList(fromAccountList);
			return fromAccountResponse;
		}
		
		@GetMapping("/validation")
		public ResponseEntity<Account> loginValidation(@RequestBody Account loginListBrowser){
			try {
				String accountNumberString = loginListBrowser.getAccountNumber();
				String userNameString = loginListBrowser.getUserName();
				
				List<Account> loginLists = accountRepository.findAll();
				for(Account u : loginLists) {
					String accountNumberFoundString = u.getAccountNumber().toString();
					if(accountNumberFoundString.equals(accountNumberString)) {
						Account accountNumberFoundLong = accountRepository.findById(u.getAccountNumber()).get();
						String userNameFoundString = accountNumberFoundLong.getUserName().toString();
						if(userNameFoundString.equals(userNameString)) {
							System.out.println("Account verified!");
							return null;
						}else {
							System.out.println("Incorrect info.");
							return null;
						}
					}
				}
				return null;
			} catch (Exception e) {
				if(e instanceof EntityNotFoundException) {
					System.out.println("Record not found");
				}
				return null;
			}
		}
			
	//  CREATE A NEW USER
		@PostMapping("/account")
		public Account createAccount(@RequestBody Account userFromBrowser) {
			System.out.println("Inserting : " + userFromBrowser);
			Account savedUser = accountRepository.save(userFromBrowser);
			return savedUser;
		}
		
		//UPDATE DATA INTO ACCOUNTS DATABASE ACCORDING TO ACCOUNT NUMBER
		@PutMapping("/account/{number}")
		public Account updateProduct(@PathVariable(value = "number") String accountNumber,
				@RequestBody Account accountFromBrowser) {

			Account existingAccount = accountRepository.findById(accountNumber).get();
			existingAccount.setAccountNumber(accountFromBrowser.getAccountNumber());
			existingAccount.setUserName(accountFromBrowser.getUserName());
			existingAccount.setAccountBalance(accountFromBrowser.getAccountBalance());

			Account updatedAccounts = accountRepository.save(existingAccount);
			return updatedAccounts;
		}
		
		@PutMapping("/accountFrom/{accountNumber}")
		public Account updateSourceAccount(@PathVariable(value="accountNumber") String accountNumber, @RequestBody Account accountFromClient) {
			Account existingAccount = accountRepository.findById(accountNumber).get();
			double availableBalance = existingAccount.getAccountBalance();
			double amount = accountFromClient.getAccountBalance();
			double newBalance = availableBalance - amount;
			
			existingAccount.setAccountBalance(newBalance);
			
			Account updatedAccount = accountRepository.save(existingAccount);
			return updatedAccount;
		}
		
		@PutMapping("/accountTo/{accountNumber}")
		public Account updateDestinationAccount(@PathVariable(value="accountNumber") String accountNumber, @RequestBody Account accountFromClient) {
			Account existingAccount = accountRepository.findById(accountNumber).get();
			double availableBalance = existingAccount.getAccountBalance();
			double amount = accountFromClient.getAccountBalance();
			double newBalance = availableBalance + amount;
			
			existingAccount.setAccountBalance(newBalance);
			
			Account updatedAccount = accountRepository.save(existingAccount);
			return updatedAccount;
		}
	
		
	
}
