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

import com.pritam.fabrication.ecommerce.exception.ProductException;
import com.pritam.fabrication.ecommerce.exception.UserException;
import com.pritam.fabrication.ecommerce.model.Rating;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.request.RatingRequest;
import com.pritam.fabrication.ecommerce.service.RatingService;
import com.pritam.fabrication.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

	@Autowired
	private UserService userService;
	@Autowired
	private RatingService ratingService;
	

	
	@PostMapping("/create")
	public ResponseEntity<Rating>createRatingHandler(@RequestBody RatingRequest req, @RequestHeader("Authorization")String jwt)
	throws UserException, ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		Rating rating = ratingService.createRating(req, user);
		return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>>getProductsReviewHandler(@PathVariable Long productId){
		
		List<Rating> ratings = ratingService.getProductsRating(productId);
		
		return new ResponseEntity<>(ratings,HttpStatus.ACCEPTED);
	}
}
