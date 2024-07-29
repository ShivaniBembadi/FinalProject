package com.handloomstore.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handloomstore.entity.CartItem;
import com.handloomstore.entity.Product;
import com.handloomstore.entity.User;
import com.handloomstore.repository.CartItemRepository;
import com.handloomstore.repository.ProductRepository;
import com.handloomstore.repository.UserRepository;
import com.handloomstore.exception.ResourseNotFoundException;

@Service
public class CartItemServiceImpl  {

	 @Autowired
	    private CartItemRepository cartItemRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private ProductRepository productRepository;

	    public void addProductToCart(int userId, int productId, CartItem cartItem) {
	    
	    	
	       User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

	        //cartItemRepository.save(cartItem);
	        
	        
	     // Check product availability
	        if (product.getQuantity() < cartItem.getQuantity()) {
	            throw new RuntimeException("Product not available in the requested quantity");
	        }
	        else {
	        cartItem.setUser(user);
	        cartItem.setProduct(product);

	        cartItemRepository.save(cartItem);
	        }
	        
	        // Subtract the quantity from the product's available quantity
	        product.setQuantity(product.getQuantity() - cartItem.getQuantity());
	        productRepository.save(product);

	        cartItem.setUser(user);
	        cartItem.setProduct(product);

	        cartItemRepository.save(cartItem);
	        
	    }

	    public void deleteCartItemById(int cartItemId) {
	        CartItem cartItem = cartItemRepository.findById(cartItemId)
	                .orElseThrow(() -> new RuntimeException("CartItem not found"));

	        // Add the quantity back to the product's available quantity
	        Product product = cartItem.getProduct();
	        product.setQuantity(product.getQuantity() + cartItem.getQuantity());
	        productRepository.save(product);

	        cartItemRepository.delete(cartItem);
	    }
	    
	    
	    
	    public List<CartItem> getCartItemsByUserId(int userId) {
	        return cartItemRepository.findByUserUserId(userId);

	    }


	    
	    public CartItem getCartItemById(int cartId) {
			return cartItemRepository.findById(cartId).get();
		}
		
}
