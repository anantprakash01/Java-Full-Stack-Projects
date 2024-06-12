package com.pritam.fabrication.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.fabrication.ecommerce.exception.OrderException;
import com.pritam.fabrication.ecommerce.model.Order;
import com.pritam.fabrication.ecommerce.response.ApiResponse;
import com.pritam.fabrication.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;

	
	@GetMapping("/")
	public ResponseEntity<List<Order>>getAllOrderHandler(){
		List<Order>orders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Order>confirmedOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization")String jwt) throws OrderException{
		Order order= orderService.confirmedOrder(orderId);
		return new ResponseEntity<Order>(order,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Order>shippedOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization")String jwt) throws OrderException{
		Order order = orderService.deliveredOrder(orderId);
		
		return new ResponseEntity<Order>(order, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Order>deliveredOrderHanlder(@PathVariable Long orderId,
			@RequestHeader("Authorization")String jwt)throws OrderException{
		Order order = orderService.deliveredOrder(orderId);
		
		return new ResponseEntity<Order>(order,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> canceledOrderHandler(@PathVariable Long orderId,
			@RequestHeader("Authorization")String jwt)throws OrderException{
		Order order = orderService.canceledOrder(orderId);
		return new ResponseEntity<Order>(order,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ApiResponse>deleteOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization")String jwt)throws OrderException{
		orderService.deleteOrder(orderId);
		ApiResponse res = new ApiResponse("Order Deleted Successfully",true);
		System.out.println("Delete method working...");
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
	}
}
