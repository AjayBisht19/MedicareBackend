package com.medicare.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicare.model.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer>{

}
