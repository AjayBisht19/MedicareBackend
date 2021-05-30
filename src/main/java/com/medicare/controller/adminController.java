package com.medicare.controller;

import java.io.IOException; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.medicare.dto.proReq;
import com.medicare.model.Product;
import com.medicare.service.ProductService;

@Controller
@RequestMapping("/admin")
@CrossOrigin("*")
public class adminController {
	@Autowired
	private ProductService productService;
	
	
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
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
		
		System.out.println("delete");
		this.productService.deleteProduct(id);
		return ResponseEntity.ok("Deleted");	
	}
	
	@PatchMapping("/product/{id}")
	public ResponseEntity<?> activePro(@PathVariable("id") Integer id){
		System.out.println("update req");
		productService.changeActive(id);
		return ResponseEntity.ok("Updated");
	}
	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") int id) throws IOException{
		Product product = productService.getProduct(id);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/product/{id}/editImage")
	public ResponseEntity<?> editImage(@PathVariable("id") int id,@RequestParam("image") MultipartFile Recfile) throws IOException {
		System.out.println("Original Image Byte Size - " + Recfile.getBytes().length);
		System.out.println("recfile stream " + Recfile.getInputStream());
		this.file = Recfile;
		System.out.println("received file "+Recfile);
		return ResponseEntity.ok("done");
	}

	@PostMapping("/product/{id}/editData")
	public ResponseEntity<?> editData(@PathVariable("id") int id,@RequestBody proReq proreq) throws IOException {
		System.out.println(this.file);
		Product product = productService.updateProduct(this.file,proreq,id);
		return ResponseEntity.ok(product);
	}
}
