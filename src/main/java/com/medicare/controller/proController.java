package com.medicare.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.model.Product;
import com.medicare.service.ProductService;
 
@RestController()
@CrossOrigin("*")
public class proController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getProducts() throws IOException {		
		List<Product> allProducts = productService.getAllProducts();
		return allProducts;
	}	
	
	
	@GetMapping("/products/categories")
	public String[] getCategories() throws IOException {
		String[] allProducts = productService.getAllCategories();
		return allProducts;
	}
	
	@GetMapping("/userProduct/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") int id) throws IOException{
		Product product = productService.getProduct(id);
		return ResponseEntity.ok(product);
	}
	

}
