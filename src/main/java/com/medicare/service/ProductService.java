package com.medicare.service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.dto.proReq;
import com.medicare.model.Product;
import com.medicare.model.User;
import com.medicare.repo.UserRepository;
import com.medicare.repo.productRepository;

@Service
public class ProductService {

	@Autowired
	private productRepository proRepo;
	
	@Autowired
	private UserRepository userRepository;
	

	public Product saveProduct(proReq proreq) throws IOException {	
		
		Product product = new Product();
		product.setImageURL(proreq.getImageURL());
		product.setCategory(proreq.getCategory());
		product.setName(proreq.getName());
		product.setPrice(proreq.getPrice());
		product.setQuantity(proreq.getQuantity());
		product.setSeller(proreq.getSeller());
		product.setDescr(proreq.getDescr());
		proRepo.save(product);
		return product;
	}

	public List<Product> getAllProducts() throws IOException {
				
		List<Product> products = proRepo.findAll();
		return products;
	}

	public void changeActive(int id) {
		Optional<Product> productOpt = proRepo.findById(id);
		Product product = productOpt.get();
		product.setActive(!product.getActive());
		proRepo.save(product);
	}


	public String[] getAllCategories() {
		String[] categories = proRepo.getCategories();
		return categories;
	}


	public void deleteProduct(int id) {
		Optional<Product> findById = proRepo.findById(id);
		Product product = findById.get();
		List<User> allusers = userRepository.findAll();
		for (User user : allusers) {
			if(user.getCart()!=null) {
				boolean remove = user.getCart().getProducts().remove(product);		
				if(remove) {
					user.getCart().setTotalAmount(user.getCart().getTotalAmount()-product.getPrice());
				}
			}
		}
		proRepo.deleteById(id);
	}
	
	public Product getProduct(int id) throws IOException {
		Optional<Product> product1 = proRepo.findById(id);
		Product product = product1.get();
		return product;
	}

	public Product updateProduct(proReq proreq,int id) throws IOException {	

		Optional<Product> findById = proRepo.findById(id);
		Product product=findById.get();
		product.setImageURL(proreq.getImageURL());
		product.setCategory(proreq.getCategory());
		product.setName(proreq.getName());
		product.setPrice(proreq.getPrice());
		product.setQuantity(proreq.getQuantity());
		product.setSeller(proreq.getSeller());
		product.setDescr(proreq.getDescr());
		proRepo.save(product);
		return product;
	}
}
