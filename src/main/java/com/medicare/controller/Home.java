package com.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.medicare.dto.EmailRequest;
import com.medicare.dto.UserRequest;
import com.medicare.model.User;
import com.medicare.repo.UserRepository;
import com.medicare.service.AuthService;
//import com.medicare.service.EmailService;

@RestController
@CrossOrigin(origins = "*")
public class Home {
	

	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserRequest userRequest) {
		User user = this.authService.signup(userRequest);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/checkUsername/{username}")
	public ResponseEntity<?> checkUsername(@PathVariable("username") String username) {
		User user = this.userRepository.findByUsername(username);
		System.out.println(user);
		if (user == null) {
			return ResponseEntity.ok(false);
		}
		return ResponseEntity.ok(true);
	}
}
