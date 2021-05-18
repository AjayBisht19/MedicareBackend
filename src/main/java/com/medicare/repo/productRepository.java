package com.medicare.repo;

import java.util.List; 


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository ;

import com.medicare.model.Product;

public interface productRepository extends JpaRepository<Product, Integer>{
	@Query("select p from Product p order by p.price")
	public List<Product> orderByPrice();
	
	@Query("select distinct category from Product")
	public String[] getCategories();
	
	@Query("select p from Product p where p.category =:cate")
	public List<Product> getProductByCate(@Param("cate") String cate);
	
	@Query("select p from Product p where p.name =:name")
	public List<Product> getProductByName(@Param("name") String name);
}
