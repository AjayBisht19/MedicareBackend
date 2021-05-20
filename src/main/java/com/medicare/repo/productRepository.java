package com.medicare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.medicare.model.Product;

public interface productRepository extends JpaRepository<Product, Integer> {
	@Query("select p from Product p order by p.price")
	public List<Product> orderByPrice();

	@Query("select distinct category from Product")
	public String[] getCategories();

	public List<Product> findByName(String name);

	public List<Product> findByCategory(String category);

	@Query("select p from Product p where p.active = true")
	public List<Product> findAllForUser();

	@Query("select p from Product p  where p.active = true order by p.price")
	public List<Product> orderByPriceForUser();

	@Query("select p from Product p where p.name =:name and p.active = true")
	public List<Product> findByNameForUser(@Param("name") String name);

	@Query("select p from Product p where active = true and p.category =:category")
	public List<Product> findByCategoryForUser(@Param("category") String category);

}
