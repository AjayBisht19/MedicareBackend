package com.medicare.model;

import java.util.List; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	private int totalAmount;
	@OneToOne(mappedBy = "cart")
	private User user;
	@ManyToMany
	@JoinTable(
			  name = "cart_detail", 
			  joinColumns = @JoinColumn(name = "cart_id"), 
			  inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
	
	
	
}
