
package com.gbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbs.entity.Transaction;
import com.gbs.entity.TransactionResponse;
import com.gbs.repository.TransactionRepository;

@RestController
@RequestMapping("/api")
public class TransactionController {
	@Autowired
	TransactionRepository transactionRepository;
	
	@GetMapping("/transactionResponse")
	public TransactionResponse getAllTransaction(){
		List<Transaction> transactionList = transactionRepository.findAll();
		TransactionResponse transactionsResponse = new TransactionResponse();
		transactionsResponse.setTransactionList(transactionList);
		return transactionsResponse;
	}
	
	@PostMapping("/transactionSave")
	public Transaction savedTransaction(@RequestBody Transaction transactionFromClient) {
		Transaction savedTransaction = transactionRepository.save(transactionFromClient);
		return savedTransaction;
	}
	
	@GetMapping("/transaction/response")
	public TransactionResponse getAllFromTransactionResponse() {
		List<Transaction> fromTransactionList = transactionRepository.findAll();
		TransactionResponse fromTransactionResponse = new TransactionResponse();
		fromTransactionResponse.setTransactionList(fromTransactionList);
		return fromTransactionResponse;
	}
	//INSERT DATA INTO ACCOUNT DATABASE IN TRANSACTION TABLE
	@PostMapping("/account/transfer")
	public Transaction transactionAccount(@RequestBody Transaction transactionFromBrowser) {
		Transaction saveTransaction = transactionRepository.save(transactionFromBrowser);
		return saveTransaction;
	}
}


