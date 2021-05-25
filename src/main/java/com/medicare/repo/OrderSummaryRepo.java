package com.medicare.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicare.model.OrderSummary;

public interface OrderSummaryRepo extends JpaRepository<OrderSummary, Integer>{

}
