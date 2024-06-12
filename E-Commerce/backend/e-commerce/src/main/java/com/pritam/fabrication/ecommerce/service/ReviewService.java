package com.pritam.fabrication.ecommerce.service;

import java.util.List;

import com.pritam.fabrication.ecommerce.exception.ProductException;
import com.pritam.fabrication.ecommerce.model.Review;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
}
