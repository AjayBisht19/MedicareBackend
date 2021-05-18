package com.medicare.model;

import java.util.Date;

import javax.persistence.*; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String name;
	private int price;
	private String category;
	private int quantity;
	private String imageName;
	private String seller;
	private String descr;
	private Boolean active=true;
	private byte[] image;
	private Date date=new Date();
	
}
