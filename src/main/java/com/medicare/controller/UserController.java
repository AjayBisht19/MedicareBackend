package com.medicare.controller;

import java.io.IOException; 
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
import com.medicare.model.Product;
import com.medicare.model.User;
import com.medicare.repo.CartRepository;
import com.medicare.repo.UserRepository;
import com.medicare.repo.productRepository;
import com.medicare.service.CartService;
import com.medicare.service.UserService;


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
	private productRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartService cartService;
	
	
	@GetMapping("/products")
	public List<Product> getProducts() throws IOException {

		List<Product> allProducts = userService.getAllProducts();
		for (Product product : allProducts) {
			System.out.println(" product "+ product.getName());
		}
		return allProducts;
	}	
	
	@GetMapping("/products/sortByPrice")
	public List<Product> getProductsByPrice() throws IOException {

		List<Product> allProducts = userService.getAllProductsByPrice();
		return allProducts;
	}
	
	@GetMapping("/products/categories")
	public String[] getCategories() throws IOException {

		String[] allProducts = userService.getAllCategories();
		return allProducts;
	}
	
	@GetMapping("/userProduct/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") int id) throws IOException{
		
		System.out.println("receivedd id "+ id);
		Product product = userService.getProduct(id);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/products/{category}")
	public List<Product> getByCategory(@PathVariable("category") String category) throws IOException {

		List<Product> allProducts = userService.getByCategory(category);
		return allProducts;
	}
	
	@GetMapping("/product/{name}")
	public List<Product> getByName(@PathVariable("name") String name) throws IOException {

		List<Product> allProducts = userService.getByName(name);
		return allProducts;
	}
	
	@GetMapping("/product/{id}/addToCart")
	public ResponseEntity<?> addToCart(Principal principal,@PathVariable("id") int id) throws IOException{
		User user = this.userRepository.findByUsername(principal.getName());
		Optional<Product> product = this.productRepository.findById(id);	
		
		 Cart cart = user.getCart();
		 cart.setTotalAmount(cart.getTotalAmount()+product.get().getPrice());
		cart.getProducts().add(product.get());
		this.cartRepository.save(cart);
		
		return ResponseEntity.ok("Added to cart");
	}
	
	@DeleteMapping("/product/{id}/removeFromCart")
	public ResponseEntity<?> removeFromCart(Principal principal,@PathVariable("id") int id) throws IOException{
		User user = this.userRepository.findByUsername(principal.getName());
		Optional<Product> product = this.productRepository.findById(id);	
		 Cart cart = user.getCart();
		cart.getProducts().remove(product.get());
		cart.setTotalAmount(cart.getTotalAmount()-product.get().getPrice());
		this.cartRepository.save(cart);
		
		return ResponseEntity.ok("Remove from cart");
	}
	
	@GetMapping("/getCart")
	public ResponseEntity<?> getCart(Principal principal) throws IOException{
		User user = this.userRepository.findByUsername(principal.getName());
		Cart cart = user.getCart();
		List<Product> products = this.cartService.getProducts(cart);
		return ResponseEntity.ok(products);
	}
	 
	
	@GetMapping("/cartAmount")
	public int getCartAmount(Principal principal) {
		User user = this.userRepository.findByUsername(principal.getName());
		Cart cart = user.getCart();
		return cart.getTotalAmount();
	}
	
	@PostMapping("/changeAddress")
	public ResponseEntity<?> changeAddress(@RequestBody String add,Principal principal){
		User user = this.userRepository.findByUsername(principal.getName());
		user.setAddress(add);
		this.userRepository.save(user);
		return ResponseEntity.ok(user);
	}
}
