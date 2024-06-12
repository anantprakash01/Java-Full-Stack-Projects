package com.pritam.fabrication.ecommerce.service;

import com.pritam.fabrication.ecommerce.exception.CartItemException;
import com.pritam.fabrication.ecommerce.exception.UserException;
import com.pritam.fabrication.ecommerce.model.Cart;
import com.pritam.fabrication.ecommerce.model.CartItem;
import com.pritam.fabrication.ecommerce.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)
			throws CartItemException, UserException;
	public CartItem isCartItemExist(Cart cart,Product product, String size, Long userId);
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException,UserException;
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
