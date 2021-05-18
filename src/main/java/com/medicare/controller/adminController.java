package com.medicare.controller;

import java.io.IOException; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.medicare.dto.proReq;
import com.medicare.model.Product;
import com.medicare.repo.productRepository;
import com.medicare.service.ProductService;

@Controller
@RequestMapping("/admin")
@CrossOrigin("*")
public class adminController {
	@Autowired
	private ProductService productService;
	@Autowired
	private productRepository proRepo;
	
	private MultipartFile file;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile Recfile) throws IOException {
		System.out.println("Original Image Byte Size - " + Recfile.getBytes().length);
		System.out.println("recfile stream " + Recfile.getInputStream());
		this.file = Recfile;

		return ResponseEntity.ok("done");
	}

	@PostMapping("/uploadData")
	public ResponseEntity<?> uploadData(@RequestBody proReq proreq) throws IOException {
		Product product = productService.saveProduct(this.file,proreq);
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deletePro(@PathVariable("id") Integer id){
		System.out.println("delete");
		proRepo.deleteById(id);
		return ResponseEntity.ok("Deleted");	
	}
	
	@PatchMapping("/product/{id}")
	public ResponseEntity<?> activePro(@PathVariable("id") Integer id){
		System.out.println("update req");
		productService.changeActive(id);
		return ResponseEntity.ok("Updated");
	}
}
