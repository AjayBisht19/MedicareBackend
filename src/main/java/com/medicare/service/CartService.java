package com.medicare.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.medicare.model.Cart;
import com.medicare.model.Product;

@Service
public class CartService {

	

	
	public List<Product> getProducts(Cart cart) throws IOException {
		List<Product> products = cart.getProducts();
		for (Product product : products) {
			System.out.println("product for users "+product.getImageName());
			File saveFile=new ClassPathResource("static").getFile();
			Path destination = Paths.get(saveFile.getAbsolutePath() + File.separator + product.getImageName());// retrieve the image by
			// its name
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}
		return products;
	}

}
