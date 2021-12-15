package com.gbs.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbs.entity.User;
import com.gbs.entity.UserResponse;
import com.gbs.repository.UserRepository;



@RestController
@RequestMapping("/api")
public class UserController {
//	SPRING FRAMEWORK CREATES THE INSTANCE OF REPOSITORY.
	@Autowired
	UserRepository userRepository;

	@GetMapping("/loginResponse")
	public UserResponse getAllUserResponse() {
		List<User> userList = userRepository.findAll();
		UserResponse userResponse = new UserResponse();
		userResponse.setUserList(userList);
		System.out.println("Getting User");
		return userResponse;
	}
	
//READ THE USERS DATABASE AND GET THE COLLECTION
	@GetMapping("/Users/response")
	public UserResponse getAllUsersResponse() {
		List<User> userList = userRepository.findAll();
		UserResponse userResponse = new UserResponse();
		userResponse.setUserList(userList);
		return userResponse;
	}
	
// GET ALL THE USERS
	@GetMapping("/usersResponse")
	public UserResponse getUserList() {
		List<User> userList = userRepository.findAll();
		UserResponse userResponse = new UserResponse();
		userResponse.setUserList(userList);
		System.out.println("Getting User");
		return userResponse;
	}

// FOR VALIDATION IN POSTMAN
	@GetMapping("/validation")
	public ResponseEntity<User> loginValidation(@RequestBody User loginListBrowser){
		try {
			String userNameString = loginListBrowser.getUserName();
			String passString = loginListBrowser.getUserPassword();
			
			List<User> loginLists = userRepository.findAll();
			for(User u : loginLists) {
				String userNameFoundString = u.getUserName().toString();
				if(userNameFoundString.equals(userNameString)) {
					User userIdFoundLong = userRepository.findById(u.getUserName()).get();
					String passwordFoundString = userIdFoundLong.getUserPassword().toString();
					if(passwordFoundString.equals(passString)) {
						System.out.println("Login success!");
						return null;
					}else {
						System.out.println("Incorrect password.");
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

	// GET ALL THE USERS
	@GetMapping("/Users")
	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		System.out.println("Getting User");
		return userList;
	}
	
//  CREATE A NEW USER
	@PostMapping("/Users")
	public User createUser(@RequestBody User userFromBrowser) {
		System.out.println("Inserting : " + userFromBrowser);
		User savedUser = userRepository.save(userFromBrowser);
		return savedUser;
	}
	
//  UPDATE EXISTING USER
	@PutMapping("/Users")
	public User updateUsers(@RequestBody User userFromBrowser) {
		System.out.println("Updating : " + userFromBrowser);
		User updatedUser = userRepository.save(userFromBrowser);
		return updatedUser;
	}
// UPDATE USER IN POSTMAN	
	@PutMapping("/Users/{userName}")
	public User updateUser(@PathVariable(value = "userName") String userName, @RequestBody User userFromBrowser) {

		User existingUser = userRepository.findById(userName).get();
		existingUser.setUserName(userFromBrowser.getUserName());
		existingUser.setUserPassword(userFromBrowser.getUserPassword());
		existingUser.setCreationDate(userFromBrowser.getCreationDate());
		existingUser.setNumberOfAccounts(userFromBrowser.getNumberOfAccounts());
		existingUser.setTotalBalance(userFromBrowser.getTotalBalance());
		existingUser.setContactNumber(userFromBrowser.getContactNumber());

		User updatedUser = userRepository.save(existingUser);
		return updatedUser;
	}
	
//	DELETE AN EXISTING USER
	@DeleteMapping("/Users/{userName}")
	public void deleteUser(@PathVariable(value = "userName") String userName) {
		System.out.println("Deleting : " + userName);
		userRepository.deleteById(userName);
	}
	
	@PutMapping("/updateFromAccountBalance/{userName}")
	public User updateFromAccountBalance(@PathVariable(value="userName") String userName, @RequestBody User userFromClient) {
		User existingUser = userRepository.findById(userName).get();
		double total = userFromClient.getTotalBalance();
		existingUser.setTotalBalance(existingUser.getTotalBalance() - total);
		User updatedUser= userRepository.save(existingUser);
		
		return updatedUser ;
	}
	
	@PutMapping("/updateToAccountBalance/{userName}")
	public User updateToAccountBalance(@PathVariable(value="userName") String userName, @RequestBody User userFromClient) {
		User existingUser = userRepository.findById(userName).get();
		double total = userFromClient.getTotalBalance();
		existingUser.setTotalBalance(existingUser.getTotalBalance() - total);
		User updatedUser= userRepository.save(existingUser);
		
		return updatedUser ;
	}
	
}