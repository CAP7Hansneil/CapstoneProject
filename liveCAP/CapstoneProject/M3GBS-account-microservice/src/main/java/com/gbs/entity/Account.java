package com.gbs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
    @Id
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "ACCOUNT_BALANCE")
	private double accountBalance;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String accountNumber, String userName, double accountBalance) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.accountBalance = accountBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

}