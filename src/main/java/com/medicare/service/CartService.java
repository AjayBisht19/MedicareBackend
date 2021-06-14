package com.medicare.service;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

import com.medicare.model.Cart;
import com.medicare.model.Product;

@Service
public class CartService {
	
	public List<Product> getProducts(Cart cart) throws IOException {
		List<Product> products = cart.getProducts();
		return products;
	}

}
