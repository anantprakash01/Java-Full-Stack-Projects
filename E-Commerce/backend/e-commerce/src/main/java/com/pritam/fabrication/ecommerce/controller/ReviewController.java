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
import com.pritam.fabrication.ecommerce.model.Review;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.request.ReviewRequest;
import com.pritam.fabrication.ecommerce.service.ReviewService;
import com.pritam.fabrication.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/create")
	public ResponseEntity<Review>createReviewHandler(@RequestBody ReviewRequest req,@RequestHeader("Authorization")String jwt)
	throws ProductException, UserException{
		
		User user = userService.findUserProfileByJwt(jwt);
		System.out.println("product id "+req.getProductId()+"-"+req.getReview());
		Review review = reviewService.createReview(req, user);
		System.out.println("product reivew "+req.getReview());
		
		return new ResponseEntity<Review>(review,HttpStatus.ACCEPTED);
	}
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>>getProductsReviewHandler(@PathVariable Long productId){
		List<Review>reviews = reviewService.getAllReview(productId);
		
		return new ResponseEntity<List<Review>>(reviews,HttpStatus.ACCEPTED);
	}
}
