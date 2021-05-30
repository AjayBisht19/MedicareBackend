package com.medicare.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;   

@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public User() {
		super();
		this.cart=new Cart();
		this.orderSummary=new OrderSummary();
		// TODO Auto-generated constructor stub
	}
	private String email;
	@Column(unique = true)
	private String username;
	private String password;
	private String name;
	private String role;
	private Boolean isEnabled=false;
	@Column(length = 5000)
	private String address;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Cart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private OrderSummary orderSummary;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public OrderSummary getOrderSummary() {
		return orderSummary;
	}
	public void setOrderSummary(OrderSummary orderSummary) {
		this.orderSummary = orderSummary;
	}
	
	


}
