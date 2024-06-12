package com.pritam.fabrication.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>>getAllUsers(@RequestHeader("Authorization")String jwt){
		System.out.println("/api/users/profile");
		List<User> user = userService.findAllUsers();
		return new ResponseEntity<List<User>>(user,HttpStatus.ACCEPTED);
	}
}
