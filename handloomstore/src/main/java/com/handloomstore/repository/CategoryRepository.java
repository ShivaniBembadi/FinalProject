package com.handloomstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handloomstore.entity.Category;


public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
