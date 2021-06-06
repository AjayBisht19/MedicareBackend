package com.medicare.service;

import org.apache.commons.io.IOUtils; 

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	

	public Product saveProduct(MultipartFile file, proReq proreq) throws IOException {	
		File saveFile=new ClassPathResource("static").getFile();
		Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
		System.out.println("input stream "+file.getInputStream());
		System.out.println("path: "+path);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		
		
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
			File saveFile=new ClassPathResource("static").getFile();
			Path destination = Paths.get(saveFile.getAbsolutePath() + File.separator + product.getImageName());
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
		File saveFile=new ClassPathResource("static").getFile();
		Path destination = Paths.get(saveFile.getAbsolutePath() + File.separator + product.getImageName());
		product.setImage(IOUtils.toByteArray(destination.toUri()));
		return product;
	}

	public Product updateProduct(MultipartFile file, proReq proreq,int id) throws IOException {		
		File saveFile=new ClassPathResource("static").getFile();
		Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
		System.out.println("input stream "+file.getInputStream());
		System.out.println("path: "+path);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

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
