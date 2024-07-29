package com.handloomstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handloomstore.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

	List<Product> findByCategory_CategoryName(String categoryName);

	List<Product> findBySubCategory_SubCategoryName(String subCategoryName);

	List<Product> findByNameContains(String name);

}
