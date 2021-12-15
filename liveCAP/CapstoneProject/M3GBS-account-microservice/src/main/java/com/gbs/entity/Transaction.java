package com.gbs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
	@Id
	@Column(name = "TRANSACTION_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	@Column(name = "FROM_ACCOUNT")
	private String fromAccount;
	@Column(name = "TO_ACCOUNT")
	private String toAccount;
	@Column(name = "AMOUNT")
	private double amount;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int transactionId, String fromAccount, String toAccount, double amount) {
		super();
		this.transactionId = transactionId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}


	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


}