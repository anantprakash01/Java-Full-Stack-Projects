package com.pritam.fabrication.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pritam.fabrication.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	public Category findByName(String name);
	
//	@Query("Select c from Category c where c.name=:name AND c.parentCategory.name=:parentCategoryName")
	@Query("SELECT c FROM Category c JOIN c.parentCategory pc WHERE c.name = :name AND pc.name = :parentCategoryName")
	public Category findByNameAndParent(@Param("name") String name, @Param("parentCategoryName")String parentCategoryName);
}
