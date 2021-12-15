package com.gbs;

import java.util.List;
import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

import com.gbs.entity.Transaction;
import com.gbs.entity.User;
import com.gbs.entity.UserResponse;
import com.gbs.entity.TransactionResponse;
import com.gbs.entity.Account;
import com.gbs.entity.AccountResponse;

public class ClientApp {
	public static void main(String[] args) {
		String repeat;
		Scanner scanner = new Scanner(System.in);

//	MENU IN LOOP
		do {
			System.out.println("WELCOME TO GLOBAL BANKING SOLUTIONS");
			System.out.println("----------------------");
			System.out.println("[1]LOGIN \n[2]SHOW ALL ACCOUNTS \n[3]End Program");
			System.out.println("----------------------");
			System.out.println("CHOOSE A NUMBER: ");
			Scanner menuScanner = new Scanner(System.in);
			int menu = menuScanner.nextInt();
			ClientApp clientApp = new ClientApp();

			if (menu == 1) {
				clientApp.loginUser();
			} else if (menu == 2) {
				clientApp.getAllUsers();
			} else if (menu == 3) {
				System.out.println("----------------------");
				System.out.println("THANK YOU FOR USING GLOBAL BANKING SOLUTIONS.");
				System.out.println("----------------------");
				System.exit(0);
				return;
			}

			System.out.println("----------------------");
			System.out.println("CONTINUE? (Y/N)");
			System.out.println("----------------------");
			repeat = scanner.nextLine();

		} while (repeat.equalsIgnoreCase("y"));
		System.out.println("----------------------");
		System.out.println("THANK YOU FOR USING GLOBAL BANKING SOLUTIONS.");
		System.out.println("----------------------");
		System.exit(0);
	}

	public void loginUser() {
//		LOG IN AND VALIDATION FOR THE USER	
		System.out.println("----------------------");
		System.out.println("LOGIN: ");
		System.out.println("----------------------");
		System.out.print("USERNAME: ");
		Scanner scanner = new Scanner(System.in);
		String loginUser = scanner.nextLine();
		System.out.print("PASSWORD: ");
		String loginPassword = scanner.nextLine();

		RestTemplate loginRestTemplate = new RestTemplate();
		final String restApiURLString = "http://localhost:8081/ecz/api/loginResponse";
		UserResponse loginResponse = loginRestTemplate.getForObject(restApiURLString, UserResponse.class);
		boolean equal = false;
		List<User> logins = loginResponse.getUserList();

		for (User login : logins) {
			String validateusername = login.getUserName().toString();
			String validatePassword = login.getUserPassword().toString();
			boolean validation = (validateusername.equalsIgnoreCase(loginUser)
					&& validatePassword.equals(loginPassword));
			if (validation) {
				equal = true;
				System.out.println("----------------------");
				System.out.println("LOGIN SUCCESS!");
				}	 
			if (equal) {
//			MENU AFTER SUCCESSFUL LOGIN
				System.out.println("----------------------");
				System.out.println("WELCOME BACK " + loginUser.toUpperCase());
				System.out.println("----------------------");
				System.out.println(
						"[1] USER DETAILS \n[2] ACCOUNT DETAILS \n[3] TRANSFER FUNDS \n[4] TRANSACTION DEATILS \n[5] LOG OUT");
				System.out.println("CHOOSE A NUMBER: ");
				int choice = scanner.nextInt();
				scanner.nextLine();

				if (choice == 1) {
//				ACCOUNT DETAILS
					
					RestTemplate showDetailsRestTemplate = new RestTemplate();
					final String restApiURLString1 = "http://localhost:8081/ecz/api/usersResponse";
					UserResponse userResponse = showDetailsRestTemplate.getForObject(restApiURLString1,
							UserResponse.class);
					
					System.out.println("----------------------");
					System.out.println("USER DETAILS: ");
					System.out.println("----------------------");
					List<User> usersList = userResponse.getUserList();
					for (User user : usersList) {
						if (user.getUserName().equalsIgnoreCase(loginUser)) {
							System.out.println("----------------------");
							String result = "\n USERNAME: " + user.getUserName() + "\n PASSWORD: "
									+ user.getUserPassword() + "\n CREATION DATE: " + user.getCreationDate()
									+ "\n NUMBER OF ACCOUNTS: " + user.getNumberOfAccounts() + "\n TOTAL BALANCE: "
									+ user.getTotalBalance() + "\n CONTACT NUMBER: " + user.getContactNumber();
							System.out.println(result);
							System.out.println("----------------------");
						}
					}
				} else if (choice == 2) {
// 				ACCOUNT DETAILS				
					
					RestTemplate accbalanceRestTemplate = new RestTemplate();
					final String restApiURLString4 = "http://localhost:9091/ecz/api/accountsResponse";
					AccountResponse accountResponse = accbalanceRestTemplate.getForObject(restApiURLString4,
							AccountResponse.class);
					
					System.out.println("----------------------");
					System.out.println("ACCOUNT DETAILS: ");
					System.out.println("----------------------");
					for (Account account : accountResponse.getAccountList()) {
						if (account.getUserName().equals(loginUser)) {
							System.out.println("\nACCOUNT NUMBER : " + account.getAccountNumber());
							System.out.println("\nUSERNAME : " + account.getUserName());
							System.out.println("\nACCOUNT BALANCE : " + account.getAccountBalance());
						}
					}

				} else if (choice == 3) {
//					TRANSFERING FUNDS
		
					RestTemplate accountRestTemplate = new RestTemplate();
					final String restApiURLString2 = "http://localhost:9091/ecz/api/accountsResponse";
					AccountResponse accountsResponse = accountRestTemplate.getForObject(restApiURLString2,
							AccountResponse.class);
					
					System.out.println("----------------------");
					System.out.println("TRANSFER FUNDS: ");
					System.out.println("----------------------");
					System.out.println("----------------------");
					System.out.println("ACCOUNT DETAILS:");
					System.out.println("----------------------");
					for (Account account : accountsResponse.getAccountList()) {
						if (account.getUserName().equals(loginUser)) {
							String result = "\n USERNAME : " + account.getUserName() + "\n ACCOUNT NUMBER: "
									+ account.getAccountNumber() + "\n ACCOUNT BALANCE: " + account.getAccountBalance();
							System.out.println(result);
						}
					}
					boolean found = false;
					System.out.println("\nENTER YOUR ACCOUNT NUMBER: ");
					String accountNumberInput = scanner.nextLine();
					for (Account account : accountsResponse.getAccountList()) {
						if (account.getUserName().equals(loginUser)) {
							if (account.getAccountNumber().equals(accountNumberInput)) {
								found = true;
							}
						}
					}
					if (!found) {
						System.out.println("INVALID ACCOUNT NUMBER.");
					} else {
						boolean tempFound = false;
						System.out.println("ENTER ACCOUNT NUMBER TO BE TRANSFERED TO: ");
						String accountTo = scanner.nextLine();

						for (Account account : accountsResponse.getAccountList()) {
							if (account.getAccountNumber().equals(accountTo)) {
								tempFound = true;
							}
						}
						if (!tempFound) {
							System.out.println("ACCOUNT NUMBER DOES NOT EXIST");
						} else {

							System.out.println("ENTER AMOUNT FUND: ");
							String tempAmount = scanner.nextLine();
							double amount = Double.parseDouble(tempAmount);
							;
							double amountBalance = 0.0;
							boolean validBalance = false;
							for (Account account : accountsResponse.getAccountList()) {
								if (account.getUserName().equals(loginUser)) {
									if (account.getAccountNumber().equals(accountNumberInput)) {
										amountBalance = account.getAccountBalance();
										if (amountBalance >= amount) {
											validBalance = true;
										}
									}
								}
							}
							if (!validBalance) {
								System.out.println("INSUFFICIENT BALANCE");
							} else {
								RestTemplate accountFromRestTemplate = new RestTemplate();
								Account accountFromUpdate = new Account();
								accountFromUpdate.setAccountBalance(amount);

								final String urlRESTAPIUpdateaccountFrom = "http://localhost:9091/ecz/api/accountFrom/"
										+ accountNumberInput;
								accountFromRestTemplate.put(urlRESTAPIUpdateaccountFrom, accountFromUpdate);

								updateTotalBalanceFromAccount(loginUser, accountTo, accountNumberInput, amount);

								RestTemplate accountToRestTemplate = new RestTemplate();
								Account accountToUpdate = new Account();
								accountToUpdate.setAccountBalance(amount);
								final String urlRESTAPIUpdateAccountTo = "http://localhost:9091/ecz/api/accountTo/"
										+ accountTo;
								accountToRestTemplate.put(urlRESTAPIUpdateAccountTo, accountToUpdate);
								double amountAccountTo = amount + accountToUpdate.getAccountBalance();
								saveTransaction(accountNumberInput, accountTo, amount, loginUser);

							}
						}
					}

				} else if (choice == 4) {
//				DISPLAY TRANSACTION DETAILS	
					
					RestTemplate transactionRestTemplate = new RestTemplate();
					final String restApiURLString5 = "http://localhost:9091/ecz/api/transactionResponse";
					TransactionResponse transactionsResponse = transactionRestTemplate.getForObject(restApiURLString5,
							TransactionResponse.class);
					System.out.println("----------------------");
					System.out.println("TRANSACTION DETAILS: ");
					System.out.println("----------------------");
					for (Transaction transaction : transactionsResponse.getTransactionList()) {
						System.out.println("--------------------------------------------");
						System.out.println("TRANSACTION ID: " + transaction.getTransactionId());
						System.out.println("ACCOUNT NUMBER FROM: " + transaction.getFromAccount());
						System.out.println("FUND TRANSFER TO: " + transaction.getToAccount());
						System.out.println("AMOUNT: " + transaction.getAmount());
						System.out.println("--------------------------------------------");
					}

				} else if (choice == 5) {
//				DISPLAY LOGOUT ACCOUNT	
					System.out.println("----------------------");
					System.out.println("ACCOUNT LOGGED OUT.");
					System.out.println("----------------------");
					return;
				}
			} 
		}
	}

// SHOWING ALL USERS WITH DETAILS
	public void getAllUsers() {

		System.out.println("----------------------");
		System.out.println("SHOWING ALL USERS: ");
		System.out.println("----------------------");

		RestTemplate userRestTemplate = new RestTemplate();
		final String restApiURLString = "http://localhost:8081/ecz/api/loginResponse";
		UserResponse userResponse = userRestTemplate.getForObject(restApiURLString, UserResponse.class);

		for (User user : userResponse.getUserList()) {
			System.out.println("--------------------------------------------");
			System.out.println("\nUSERNAME: " + user.getUserName() + "\nPASSWORD: " + user.getUserPassword()
					+ "\nDATE CREATED: " + user.getCreationDate() + "\nNUMBER OF ACCOUNTS: "
					+ user.getNumberOfAccounts() + "\nTOTAL BALANCE: " + user.getTotalBalance() + "\nCONTACT NUMBER: "
					+ user.getContactNumber());
			System.out.println("--------------------------------------------");
		}
	}

//	UPDATING TOTAL BALANCE IN ACCOUNT
	public static void updateTotalBalanceFromAccount(String userName, String accountTo, String accountFrom,
			Double amount) {

		double currentBal = 0.0;
		double totalBal = 0.0;
		boolean found = false;

		RestTemplate totalBalanceRestTemplate = new RestTemplate();
		final String restApiURLString = "http://localhost:9091/ecz/api/accountsResponse";
		AccountResponse accountsResponse = totalBalanceRestTemplate.getForObject(restApiURLString,
				AccountResponse.class);

		for (Account account : accountsResponse.getAccountList()) {
			if (account.getUserName().equals(userName)) {
				if (account.getAccountNumber().equals(accountFrom)) {
					RestTemplate userFromRestTemplate = new RestTemplate();
					User userUpdateFrom = new User();
					userUpdateFrom.setTotalBalance(amount);
					final String urlRESTAPIUpdateUserFrom = "http://localhost:8081/ecz/api/updateFromAccountBalance/"
							+ userName;
					userFromRestTemplate.put(urlRESTAPIUpdateUserFrom, userUpdateFrom);
				}
			} else {

				if (account.getAccountNumber().equals(accountTo)) {
					String userNameFrom = account.getUserName();
					User userUpdateFrom = new User();
					userUpdateFrom.setTotalBalance(amount);
					RestTemplate userFromRestTemplate = new RestTemplate();
					String urlRESTAPIUpdateUserFrom = "http://localhost:8081/ecz/api/updateFromAccountBalance/"
							+ userNameFrom;
					userFromRestTemplate.put(urlRESTAPIUpdateUserFrom, userUpdateFrom);
				}
			}
		}
	}

//	SAVING TO TRANSACTION 
	public static void saveTransaction(String accountFrom, String accountTo, double amount, String userName) {
		RestTemplate transactionsRestTemplate = new RestTemplate();
		final String restApiURLString3 = "http://localhost:9091/ecz/api/transactionSave";
		Transaction transaction = new Transaction();
		transaction.setFromAccount(accountFrom);
		transaction.setToAccount(accountTo);
		transaction.setAmount(amount);
		transactionsRestTemplate.postForObject(restApiURLString3, transaction, Transaction.class);

		RestTemplate accountRestTemplate = new RestTemplate();
		final String restAccountApiURLString = "http://localhost:9091/ecz/api/accountsResponse";
		AccountResponse accountsResponse = accountRestTemplate.getForObject(restAccountApiURLString,
				AccountResponse.class);
		System.out.println(
				"\nYOU TRANSFER FUND TO ACCOUNT NUMBER: " + accountTo + " AND A AMOUNT OF " + amount + " SUCCESFULLY");
		System.out.println("ACCOUNT UPDATED! ");
		for (Account account : accountsResponse.getAccountList()) {
			if (account.getUserName().equals(userName))
				if (account.getAccountNumber().equals(accountFrom)) {
					System.out.println("--------------------------------------------");
					String result = "\n USERNAME : " + account.getAccountNumber() + "\n ACCOUNT NUMBER: "
							+ account.getUserName() + "\n NEW ACCOUNT BALANCE: " + account.getAccountBalance();
					System.out.println(result);
					System.out.println("--------------------------------------------");
				}
		}
	}
}