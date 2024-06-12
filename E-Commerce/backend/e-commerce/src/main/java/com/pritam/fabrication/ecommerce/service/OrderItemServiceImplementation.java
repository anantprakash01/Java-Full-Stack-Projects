package com.pritam.fabrication.ecommerce.service;

import org.springframework.stereotype.Service;

import com.pritam.fabrication.ecommerce.model.OrderItem;
import com.pritam.fabrication.ecommerce.repository.OrderItemRepository;


@Service
public class OrderItemServiceImplementation implements OrderItemService{

	private OrderItemRepository orderItemRepository;
	
	
	
	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		super();
		this.orderItemRepository = orderItemRepository;
	}



	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {

		return orderItemRepository.save(orderItem);
	}

}
