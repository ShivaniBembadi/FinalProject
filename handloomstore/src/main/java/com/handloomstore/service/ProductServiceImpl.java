package com.handloomstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handloomstore.entity.Product;
import com.handloomstore.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductServiceImpl {

	@Autowired
	private ProductRepository productRepository;

	// Add product
	
	public Product addProduct(@Valid Product product) {
		return productRepository.save(product);
	}

	// Updating Product
	
	public Product updateProduct(Product product, @Valid int productId) {
		Product existingProduct = productRepository.findById(productId).get();
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setDescription(product.getDescription());
		// existingProduct.setStatus(product.getStatus());
		existingProduct.setQuantity(product.getQuantity());

		productRepository.save(existingProduct);
		return existingProduct;
	}

	// Deleting product
	
	public void deleteProduct(int productId) {
		productRepository.findById(productId).get();
		productRepository.deleteById(productId);

	}

	// Get All products

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	// Get product By Id

	public Product getProductById(int productId) {
		return productRepository.findById(productId).get();
	}

	// GetProduct By name

	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}

	// Get Products By Category

	public List<Product> findByCategoryName(String categoryName) {
		return productRepository.findByCategory_CategoryName(categoryName);
	}

	// Get products By Sub catagory

	public List<Product> findBySubCategoryName(String subCategoryName) {
		return productRepository.findBySubCategory_SubCategoryName(subCategoryName);
	}

}
