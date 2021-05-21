package com.medicare.model;

import javax.persistence.CascadeType; 
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public User() {
		super();
		this.cart=new Cart();
		// TODO Auto-generated constructor stub
	}
	private String email;
	private String username;
	private String password;
	private String name;
	private String role;
	private Boolean isEnabled=false;
	private String address;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Cart cart;

}
