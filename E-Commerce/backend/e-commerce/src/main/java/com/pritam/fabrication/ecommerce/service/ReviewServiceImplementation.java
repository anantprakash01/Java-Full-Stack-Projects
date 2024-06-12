package com.pritam.fabrication.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pritam.fabrication.ecommerce.exception.ProductException;
import com.pritam.fabrication.ecommerce.model.Product;
import com.pritam.fabrication.ecommerce.model.Review;
import com.pritam.fabrication.ecommerce.model.User;
import com.pritam.fabrication.ecommerce.repository.ProductRepository;
import com.pritam.fabrication.ecommerce.repository.ReviewRepository;
import com.pritam.fabrication.ecommerce.request.ReviewRequest;


@Service
public class ReviewServiceImplementation implements ReviewService{

	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		super();
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		
		Product product = productService.findProductById(req.getProductId());
		
		Review review = new Review();
		
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		productRepository.save(product);
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		// TODO Auto-generated method stub
		return reviewRepository.getAllProductsReview(productId);
	}

}
