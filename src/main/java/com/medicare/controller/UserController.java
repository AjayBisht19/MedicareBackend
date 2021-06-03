package com.medicare.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medicare.model.Cart;
import com.medicare.model.OrderDetails;
import com.medicare.model.OrderSummary;
import com.medicare.model.Product;
import com.medicare.model.User;
import com.medicare.repo.CartRepository;
import com.medicare.repo.OrderDetailsRepo;
import com.medicare.repo.UserRepository;
import com.medicare.service.CartService;
import com.medicare.service.UserService;
import com.razorpay.*;

@Controller
@RequestMapping("/user")
@CrossOrigin("*")
@ResponseBody
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderDetailsRepo orderDetailsRepo;

	private List<Product> orderProd;

	@GetMapping("/products")
	public List<Product> getProducts() throws IOException {
		List<Product> allProducts = userService.getAllProducts();
		return allProducts;
	}

	@GetMapping("/products/categories")
	public String[] getCategories() throws IOException {
		String[] allProducts = userService.getAllCategories();
		return allProducts;
	}

	@GetMapping("/userProduct/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") int id) throws IOException {
		Product product = userService.getProduct(id);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/product/{id}/addToCart")
	public ResponseEntity<?> addToCart(Principal principal, @PathVariable("id") int id) throws IOException {
		User user = this.userRepository.findByUsername(principal.getName());
		this.userService.addToCart(user, id);
		return ResponseEntity.ok("Added to cart");
	}

	@DeleteMapping("/product/{id}/removeFromCart")
	public ResponseEntity<?> removeFromCart(Principal principal, @PathVariable("id") int id) throws IOException {
		User user = this.userRepository.findByUsername(principal.getName());
		this.userService.removeFromCart(user, id);
		return ResponseEntity.ok("Remove from cart");
	}

	@GetMapping("/getCart")
	public ResponseEntity<?> getCart(Principal principal) throws IOException {
		User user = this.userRepository.findByUsername(principal.getName());
		Cart cart = user.getCart();
		List<Product> products = this.cartService.getProducts(cart);
		this.orderProd = products;
		return ResponseEntity.ok(products);
	}

	@GetMapping("/cartAmount")
	public int getCartAmount(Principal principal) {
		User user = this.userRepository.findByUsername(principal.getName());
		Cart cart = user.getCart();
		return cart.getTotalAmount();
	}

	@PostMapping("/changeAddress")
	public ResponseEntity<?> changeAddress(@RequestBody String add, Principal principal) {
		User user = this.userRepository.findByUsername(principal.getName());
		user.setAddress(add);
		this.userRepository.save(user);
		return ResponseEntity.ok(user);
	}

	@GetMapping(value = "/createOrder")
	public ResponseEntity<?> createOrder(Principal principal) throws RazorpayException {
		System.out.println("payment request");
		User user = this.userRepository.findByUsername(principal.getName());
		Order order = this.userService.createOrder(user);
		return ResponseEntity.ok(order.toString());
	}

	@GetMapping("/createOrderSummary/{orderUID}")
	public ResponseEntity<?> createOrderSummary(Principal principal, @PathVariable("orderUID") String orderUID) {
		User user = this.userRepository.findByUsername(principal.getName());
		Cart cart = user.getCart();
		cart.getProducts().removeAll(cart.getProducts());

		OrderSummary orderSummary = user.getOrderSummary();
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderSummary(orderSummary);
		orderDetails.setOrderUID(orderUID);
		orderDetails.setTotalAmount(cart.getTotalAmount());
		cart.setTotalAmount(0);
		this.cartRepository.save(cart);

		orderDetails.setProducts(this.orderProd);
		this.orderProd = null;
		this.orderDetailsRepo.save(orderDetails);
		return ResponseEntity.ok("Added to Order Summary");
	}

	@GetMapping("/getOrderProducts")
	public ResponseEntity<?> getOrderProducts(Principal principal) {
		User user = this.userRepository.findByUsername(principal.getName());
		List<OrderDetails> orderDetails = user.getOrderSummary().getOrderDetails();
		return ResponseEntity.ok(orderDetails);
	}
}
