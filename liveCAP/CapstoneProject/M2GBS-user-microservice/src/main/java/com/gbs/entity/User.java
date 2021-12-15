package com.gbs.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	@Column(name = "CREATION_DATE")
	LocalDate creationDate = LocalDate.now();
	@Column(name = "NUMBER_OF_ACCOUNTS")
	private String numberOfAccounts;
	@Column(name = "TOTAL_BALANCE")
	private Double totalBalance;
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String userPassword, LocalDate creationDate, String numberOfAccounts, Double totalBalance,
			String contactNumber) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.creationDate = creationDate;
		this.numberOfAccounts = numberOfAccounts;
		this.totalBalance = totalBalance;
		this.contactNumber = contactNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getNumberOfAccounts() {
		return numberOfAccounts;
	}

	public void setNumberOfAccounts(String numberOfAccounts) {
		this.numberOfAccounts = numberOfAccounts;
	}

	public Double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
