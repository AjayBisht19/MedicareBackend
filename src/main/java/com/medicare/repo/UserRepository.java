package com.medicare.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicare.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUsername(String username);
}
