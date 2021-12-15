package com.gbs.entity;

import java.util.List;

public class UserResponse {
	private List<User> users;

	public List<User> getUserList() {
		return users;
	}
	public void setUserList(List<User> users) {
		this.users = users;
	}

}