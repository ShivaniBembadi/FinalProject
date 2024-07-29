package com.handloomstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handloomstore.entity.Category;
import com.handloomstore.repository.CategoryRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/catagory")
public class CategoryController {
	@Autowired
	private CategoryRepository CategoryRepo;
	
	//Adding sub category
	@PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody  Category p) {
        return new ResponseEntity<Category>(CategoryRepo.save(p),HttpStatus.CREATED);
      //  return "Product sub category added successfully";
    }
	
	@GetMapping("/list")
    public ResponseEntity<List<Category>> addAllCategory() {
        return new ResponseEntity<List<Category>>(CategoryRepo.findAll(),HttpStatus.CREATED);
      //  return "Product sub category added successfully";
    }
	
	//Deleting Sub Category
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable int id) {
		CategoryRepo.deleteById(id);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
}