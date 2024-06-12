package com.pritam.fabrication.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pritam.fabrication.ecommerce.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query("SELECT r FROM Rating r WHERE r.product.id=:productId")
	public List<Review> getAllProductsReview(@Param("productId")Long productId);
}
