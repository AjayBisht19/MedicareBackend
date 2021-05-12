package com.medicare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.medicare.dto.UserRequest;
import com.medicare.model.User;
import com.medicare.repo.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepoitory;
	public User signup(UserRequest userRequest) {
		User user=new User();
		user.setAddress(userRequest.getAddress());
		user.setEmail(userRequest.getEmail());
		user.setName(userRequest.getName());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setRole("user");
		user.setUsername(userRequest.getUsername());
		this.userRepoitory.save(user);
		return user;
	}

}
