package com.pritam.fabrication.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.fabrication.ecommerce.exception.OrderException;
import com.pritam.fabrication.ecommerce.exception.UserException;
import com.pritam.fabrication.ecommerce.model.Address;
import com.pritam.fabrication.ecommerce.model.Order;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.service.OrderService;
import com.pritam.fabrication.ecommerce.service.UserService;

@RestController
@RequestMapping("api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	
	@PostMapping("/")
	public ResponseEntity<Order>createOrderHandler(@RequestBody Address shippingAddress,
			@RequestHeader("Authorization")String jwt) throws UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		Order order = orderService.createOrder(user, shippingAddress);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	
	@GetMapping("/user")
	public ResponseEntity<List<Order>>userOrderHistoryHandler(@RequestHeader("Authorization")String jwt)
	throws OrderException,UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		List<Order> orders = orderService.usersOrderHistory(user.getId());
		return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
		
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Order> findOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt)
	throws OrderException,UserException{
		User user = userService.findUserProfileByJwt(jwt);
		Order orders = orderService.findOrderById(orderId);
		return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
	}
	
	
}
