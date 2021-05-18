package com.medicare.controller;

import java.io.IOException; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
	
//	@GetMapping("/products/sortByDate")
//	public List<Product> getProductsByDate() throws IOException {
//
//		List<Product> allProducts = productService.getAllProductsByDate();
//		return allProducts;
//	}
	
	@GetMapping("/products/sortByPrice")
	public List<Product> getProductsByPrice() throws IOException {

		List<Product> allProducts = productService.getAllProductsByPrice();
		return allProducts;
	}
	
	@GetMapping("/products/categories")
	public String[] getCategories() throws IOException {

		String[] allProducts = productService.getAllCategories();
		return allProducts;
	}
	
	@GetMapping("/products/{category}")
	public List<Product> getByCategory(@PathVariable("category") String category) throws IOException {

		List<Product> allProducts = productService.getByCategory(category);
		return allProducts;
	}
	@GetMapping("/product/{name}")
	public List<Product> getByName(@PathVariable("name") String name) throws IOException {

		List<Product> allProducts = productService.getByName(name);
		return allProducts;
	}

}