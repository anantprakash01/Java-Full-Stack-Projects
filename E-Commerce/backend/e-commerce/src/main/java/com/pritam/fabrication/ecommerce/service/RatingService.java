package com.pritam.fabrication.ecommerce.service;

import java.util.List;

import com.pritam.fabrication.ecommerce.exception.ProductException;
import com.pritam.fabrication.ecommerce.model.Rating;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req, User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);
}
