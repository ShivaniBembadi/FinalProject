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
import com.handloomstore.entity.SubCategory;
import com.handloomstore.repository.SubCategoryRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/subcatagory")
public class SubCategoryController {
	@Autowired
	private SubCategoryRepository subCategoryRepo;

	// Adding sub category
	@PostMapping("/add")
	public ResponseEntity<SubCategory> addSubCategory(@RequestBody SubCategory p) {
		return new ResponseEntity<SubCategory>(subCategoryRepo.save(p), HttpStatus.CREATED);
		// return "Product sub category added successfully";
	}

	// Deleting Sub Category
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteSubCategoryById(@PathVariable int id) {
		subCategoryRepo.deleteById(id);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);

	}
	
	

	@GetMapping("/list")
	public List<SubCategory> getAllProduct() {
		return subCategoryRepo.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public SubCategory getSubCategoryBySubCategoryId(@PathVariable int id) {
		return subCategoryRepo.getBySubCategoryId(id);
	}

}
