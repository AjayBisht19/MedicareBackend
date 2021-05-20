package com.medicare.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.model.Product;
import com.medicare.repo.UserRepository;
import com.medicare.repo.productRepository;

@Service
public class UserService {
	
	@Autowired
	private productRepository proRepo;
	
	
	
	public final String storageDirectoryPath = "C://Medicare";
	
	public List<Product> getAllProducts() throws IOException {
		
		List<Product> products = proRepo.findAllForUser();
		
		for (Product product : products) {
			System.out.println("product for users "+product.getImageName());
			Path destination = Paths.get(storageDirectoryPath + "\\" + product.getImageName());// retrieve the image by
			// its name
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}
		return products;
	}
	
	public List<Product> getAllProductsByPrice() throws IOException {
		List<Product> orderByPrice = proRepo.orderByPriceForUser();
		for (Product product : orderByPrice) {
			System.out.println(product.getImageName());
			Path destination = Paths.get(storageDirectoryPath + "\\" + product.getImageName());// retrieve the image by
			// its name
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}
		return orderByPrice;
	}

	public String[] getAllCategories() {
		// TODO Auto-generated method stub

		String[] categories = proRepo.getCategories();
		System.out.println("categories "+ categories );
		return categories;
	}

	public List<Product> getByCategory(String category) throws IOException {
		List<Product> productByCategory = proRepo.findByCategoryForUser(category);
		for (Product product : productByCategory) {
			System.out.println(product.getImageName());
			Path destination = Paths.get(storageDirectoryPath + "\\" + product.getImageName());// retrieve the image by
			// its name
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}
		return productByCategory;
	}

	public List<Product> getByName(String name) throws IOException {
		List<Product> productByName = proRepo.findByNameForUser(name);
		for (Product product : productByName) {
			System.out.println(product.getImageName());
			Path destination = Paths.get(storageDirectoryPath + "\\" + product.getImageName());
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}
		return productByName;
	}

	public Product getProduct(int id) throws IOException {
		// TODO Auto-generated method stub
		Optional<Product> product1 = proRepo.findById(id);
		Product product = product1.get();
		Path destination = Paths.get(storageDirectoryPath + "\\" + product.getImageName());
		product.setImage(IOUtils.toByteArray(destination.toUri()));
		return product;
	}
	
	public void addToCart(Product product) {
		
	}

}
