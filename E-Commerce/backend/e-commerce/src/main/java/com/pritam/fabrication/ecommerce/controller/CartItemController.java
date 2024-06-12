package com.pritam.fabrication.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.fabrication.ecommerce.exception.CartItemException;
import com.pritam.fabrication.ecommerce.exception.UserException;
import com.pritam.fabrication.ecommerce.model.CartItem;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.response.ApiResponse;
import com.pritam.fabrication.ecommerce.service.CartItemService;
import com.pritam.fabrication.ecommerce.service.UserService;

//import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cart_items")
//@Tag(name="Cart Item Management", description="create cart item delete cart item")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private UserService userService;
	
	
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItemHandler(@PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt)
	throws CartItemException, UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);
		
		ApiResponse res = new ApiResponse("Item removed from cart",true);
		
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem>updateCartItemHandler(@PathVariable Long cartItemId, @RequestBody CartItem cartItem,@RequestHeader("Authorization")String jwt)
	throws CartItemException, UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		
		CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(),cartItemId,cartItem);
		
		return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);
	}
}
