package com.pritam.fabrication.ecommerce.request;

public class RatingRequest {

	private Long productId;
	private double rating;
	
	public Long getProductId() {
		return productId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
}
