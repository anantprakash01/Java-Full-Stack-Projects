package com.pritam.fabrication.ecommerce.service;

import com.pritam.fabrication.ecommerce.exception.ProductException;
import com.pritam.fabrication.ecommerce.model.Cart;
import com.pritam.fabrication.ecommerce.model.CartItem;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	public CartItem addCartItem(Long userId, AddItemRequest req) throws ProductException;
	public Cart findUserCart(Long userId);
	
}
