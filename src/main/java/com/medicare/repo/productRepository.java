package com.medicare.repo;

import java.util.List; 

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.medicare.model.Product;

public interface productRepository extends JpaRepository<Product, Integer> {
	

	@Query("select distinct category from Product")
	public String[] getCategories();


	@Query("select p from Product p where p.active = true")
	public List<Product> findAllForUser();


}
