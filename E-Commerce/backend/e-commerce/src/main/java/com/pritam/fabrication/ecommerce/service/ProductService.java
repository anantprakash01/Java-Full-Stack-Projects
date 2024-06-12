package com.pritam.fabrication.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pritam.fabrication.ecommerce.exception.ProductException;
import com.pritam.fabrication.ecommerce.model.Product;

import com.pritam.fabrication.ecommerce.request.CreateProductRequest;

public interface ProductService {

//	only for admin
	public Product createProduct(CreateProductRequest req)throws ProductException;
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(long productId, Product erq) throws ProductException;
	
	public List<Product> getAllProducts();
	
	
//	for user and admin both
	public Product findProductById(Long id)throws ProductException;
	
	public List<Product> searchProduct(String query);
	
	public Page<Product>getAllProduct(String category, List<String>colors, List<String>sizes,Integer minPric,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
	
	public List<Product> findProductByCategory(String category);
	
	public List<Product> recentlyAddedProduct();
}
