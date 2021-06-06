package com.medicare.service;

import java.io.File;   
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.razorpay.*;
import com.medicare.model.Cart;
import com.medicare.model.Product;
import com.medicare.model.User;
import com.medicare.repo.CartRepository;
import com.medicare.repo.productRepository;

@Service
public class UserService {

	@Autowired
	private productRepository proRepo;

	@Autowired
	private CartRepository cartRepository;

	public List<Product> getAllProducts() throws IOException {
		List<Product> products = proRepo.findAllForUser();
		for (Product product : products) {
			System.out.println("product for users " + product.getImageName());
			File saveFile = new ClassPathResource("static").getFile();
			Path destination = Paths.get(saveFile.getAbsolutePath() + File.separator + product.getImageName());
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}
		return products;
	}


	public String[] getAllCategories() {
		String[] categories = proRepo.getCategories();
		System.out.println("categories " + categories);
		return categories;
	}


	public Product getProduct(int id) throws IOException {
		Optional<Product> product1 = proRepo.findById(id);
		Product product = product1.get();
		File saveFile = new ClassPathResource("static").getFile();
		Path destination = Paths.get(saveFile.getAbsolutePath() + File.separator + product.getImageName());
		product.setImage(IOUtils.toByteArray(destination.toUri()));
		return product;
	}

	public Order createOrder(User user) throws RazorpayException {
		int totalAmount = user.getCart().getTotalAmount();
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_YRVcjR7aOtyudM", "dShRAdWi9VzSV87mJuA58FfD");
		JSONObject opt = new JSONObject();
		opt.put("amount", totalAmount * 100);
		opt.put("currency", "INR");
		opt.put("receipt", "txn_485976");
		Order order = razorpayClient.Orders.create(opt);
		System.out.println("Order " + order);
		return order;
	}

	public void addToCart(User user, int id) {
		Optional<Product> product = this.proRepo.findById(id);
		Cart cart = user.getCart();
		cart.setTotalAmount(cart.getTotalAmount() + product.get().getPrice());
		cart.getProducts().add(product.get());
		this.cartRepository.save(cart);
	}

	public void removeFromCart(User user, int id) {
		Optional<Product> product = this.proRepo.findById(id);
		Cart cart = user.getCart();
		cart.getProducts().remove(product.get());
		cart.setTotalAmount(cart.getTotalAmount() - product.get().getPrice());
		this.cartRepository.save(cart);
	}
}
