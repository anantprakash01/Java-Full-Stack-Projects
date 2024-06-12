package com.pritam.fabrication.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pritam.fabrication.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);

	
}
