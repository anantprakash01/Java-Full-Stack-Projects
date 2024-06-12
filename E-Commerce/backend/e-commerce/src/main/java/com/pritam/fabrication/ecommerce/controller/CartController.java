package com.pritam.fabrication.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.fabrication.ecommerce.exception.ProductException;
import com.pritam.fabrication.ecommerce.exception.UserException;
import com.pritam.fabrication.ecommerce.model.Cart;
import com.pritam.fabrication.ecommerce.model.CartItem;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.request.AddItemRequest;
import com.pritam.fabrication.ecommerce.response.ApiResponse;
import com.pritam.fabrication.ecommerce.service.CartService;
import com.pritam.fabrication.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUsrCartHandler(@RequestHeader("Authorization")String jwt)
	throws UserException{
		
		User user =userService.findUserProfileByJwt(jwt);
		
		Cart cart = cartService.findUserCart(user.getId());
		
		System.out.println("cart - "+cart.getUser().getEmail());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
		
	}
	
	@PutMapping("/add")
	public ResponseEntity<?>addItemToCart(@RequestBody AddItemRequest req,
		@RequestHeader("Authorization")String jwt) throws UserException,ProductException{
		
		User user = userService.findUserProfileByJwt(jwt);
		CartItem item = cartService.addCartItem(user.getId(), req);
		
		ApiResponse res = new ApiResponse("Item added To Cart", true);
		
		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}

}
