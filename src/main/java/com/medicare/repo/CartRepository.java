package com.medicare.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicare.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
