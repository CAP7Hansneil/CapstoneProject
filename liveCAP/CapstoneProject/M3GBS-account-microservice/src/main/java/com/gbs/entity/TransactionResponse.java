package com.gbs.entity;

import java.util.List;

public class TransactionResponse {
	private List<Transaction> transactions;

	public List<Transaction> getTransactionList() {
		return transactions;
	}
	public void setTransactionList(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}