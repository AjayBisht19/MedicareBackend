package com.medicare.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	private String email;
	private String username;
	private String password;
	private String name;
	private String role;
	private Boolean isEnabled;
	private String address;
}
