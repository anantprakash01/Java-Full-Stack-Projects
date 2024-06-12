package com.pritam.fabrication.ecommerce.service;

import java.util.List;

import com.pritam.fabrication.ecommerce.exception.OrderException;
import com.pritam.fabrication.ecommerce.model.Address;
import com.pritam.fabrication.ecommerce.model.Order;
import com.pritam.fabrication.ecommerce.model.User;

public interface OrderService {

	public Order createOrder(User user,Address shipingAddress);
	public Order findOrderById(Long orderId) throws OrderException;
	public List<Order> usersOrderHistory(Long userId);
	public Order placedOrder(Long orderId) throws OrderException;
	public Order confirmedOrder(Long orderId) throws OrderException;
	public Order shippedOrder(Long orderId) throws OrderException;
	public Order deliveredOrder(Long orderId) throws OrderException;
	public Order canceledOrder(Long orderId) throws OrderException;
	public List<Order> getAllOrders();
	public void deleteOrder(Long orderId) throws OrderException;
}
