package com.handloomstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handloomstore.entity.SubCategory;


public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer>{

	SubCategory getBySubCategoryId(int id);

}
