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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.handloomstore.entity.Product;
import com.handloomstore.repository.ProductRepository;
import com.handloomstore.service.ProductServiceImpl;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class ProductController {

	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private ProductRepository productRepo;

	// Adding Product
	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
		return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.CREATED);
	}

	// Updating Product
	@PutMapping("/updateproduct/{productId}")
	public ResponseEntity<Product> updateProduct(@Valid @PathVariable("productId") int productId,
			@RequestBody Product product) {
		return new ResponseEntity<Product>(productService.updateProduct(product, productId), HttpStatus.OK);
	}

	// Deleting Product
	@DeleteMapping("/deleteproduct/{productId}")
	public ResponseEntity<Boolean> deleteProduct(@Valid @PathVariable("productId") int productId) {
		productService.deleteProduct(productId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

	// Get Product by id
	@GetMapping("/productbyid/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") int productId) {
		return new ResponseEntity<Product>(productService.getProductById(productId), HttpStatus.OK);
	}


	@GetMapping("/search")
	public List<Product> getProductBySearch(@RequestParam String name)
	{
        return productRepo.findByNameContains(name);
    }
	
	
	// Get product by name
	@GetMapping("/productbyname/{productName}")
	public ResponseEntity<Product> getProductByName(@PathVariable("productName") String name) {
		return new ResponseEntity<Product>(productService.getProductByName(name), HttpStatus.OK);
	}

	// Get all products
	@GetMapping("/allproducts")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	// get products by category
	@GetMapping("/products/by-category/{categoryName}")
	public List<Product> getProductsByCategory(@PathVariable String categoryName) {
		return productService.findByCategoryName(categoryName);
	}

	// get products by subcategory
	@GetMapping("/products/by-subcategory/{subCategoryName}")
	public List<Product> getProductsBySubCategory(@PathVariable String subCategoryName) {
		return productService.findBySubCategoryName(subCategoryName);
	}
	
}
