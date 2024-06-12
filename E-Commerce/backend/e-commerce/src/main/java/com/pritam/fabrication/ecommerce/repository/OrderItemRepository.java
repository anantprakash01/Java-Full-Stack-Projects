package com.pritam.fabrication.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pritam.fabrication.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
