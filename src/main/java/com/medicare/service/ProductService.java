package com.medicare.service;

import org.apache.commons.io.IOUtils; 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.medicare.dto.proReq;
import com.medicare.model.Product;
import com.medicare.repo.productRepository;

@Service
public class ProductService {

	@Autowired
	private productRepository proRepo;
	
	public final String storageDirectoryPath = "C://Medicare";

	public Product saveProduct(MultipartFile file, proReq proreq) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path storageDirectory = Paths.get(storageDirectoryPath);
		System.out.println("storage path " + storageDirectory);

		if (!Files.exists(storageDirectory)) { // if the folder does not exist
			try {
				Files.createDirectories(storageDirectory); // we create the directory in the given storage directory
															// path
			} catch (Exception e) {
				System.out.println("askdfkajsdfahsd");
				e.printStackTrace();// print the exception
			}
		}

		System.out.println("file name " + fileName);
		Path destination = Paths.get(storageDirectory + "\\" + fileName);
		System.out.println("Destination " + destination);

		try {
			System.out.println("input stream error");
			System.out.println("Input Stream " + file.getInputStream());
			Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Product product = new Product();
		product.setImageName(file.getOriginalFilename());
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
		for (Product product : products) {
			System.out.println(product.getImageName());
			Path destination = Paths.get(storageDirectoryPath + "\\" + product.getImageName());// retrieve the image by
			// its name
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}
		return products;
	}

	public void changeActive(int id) {
		Optional<Product> productOpt = proRepo.findById(id);
		Product product = productOpt.get();
		product.setActive(!product.getActive());
		proRepo.save(product);
	}

	public List<Product> getAllProductsByPrice() throws IOException {
		List<Product> orderByPrice = proRepo.orderByPrice();
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
		return categories;
	}

	public List<Product> getByCategory(String category) throws IOException {
		List<Product> productByCategory = proRepo.findByCategory(category);
		for (Product product : productByCategory) {
			System.out.println(product.getImageName());
			Path destination = Paths.get(storageDirectoryPath + "\\" + product.getImageName());// retrieve the image by
			// its name
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}
		return productByCategory;
	}

	public List<Product> getByName(String name) throws IOException {
		List<Product> productByName = proRepo.findByName(name);
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

	public Product updateProduct(MultipartFile file, proReq proreq,int id) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path storageDirectory = Paths.get(storageDirectoryPath);
		System.out.println("storage path " + storageDirectory);

		if (!Files.exists(storageDirectory)) { // if the folder does not exist
			try {
				Files.createDirectories(storageDirectory); // we create the directory in the given storage directory
															// path
			} catch (Exception e) {
				System.out.println("askdfkajsdfahsd");
				e.printStackTrace();// print the exception
			}
		}

		System.out.println("file name " + fileName);
		Path destination = Paths.get(storageDirectory + "\\" + fileName);
		System.out.println("Destination " + destination);

		try {
			System.out.println("input stream error");
			System.out.println("Input Stream " + file.getInputStream());
			Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Optional<Product> findById = proRepo.findById(id);
		Product product=findById.get();
		product.setImageName(file.getOriginalFilename());
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
