package com.service;

import com.model.User;

public interface UserService {
	
	void save(User user);

	User login(String username,String password);
}
