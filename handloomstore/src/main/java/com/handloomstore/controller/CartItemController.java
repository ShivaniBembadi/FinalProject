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
import com.handloomstore.entity.CartItem;
import com.handloomstore.service.CartItemServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/cartitem")
public class CartItemController {
	@Autowired
	private CartItemServiceImpl cartItemService;

	@PostMapping("/add/{userId}/{productId}")
	public ResponseEntity<String> addProductToCart(@PathVariable int userId, @PathVariable int productId,
			@RequestBody CartItem cartItem) {
		try {
			cartItemService.addProductToCart(userId, productId, cartItem);
			return ResponseEntity.ok("Product added to cart successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<String> deleteCartItemById(@PathVariable int cartItemId) {
		try {
			cartItemService.deleteCartItemById(cartItemId);
			return ResponseEntity.ok("CartItem deleted successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<CartItem>> getCartItemsByUserId(@PathVariable int userId) {
		List<CartItem> cartItems = cartItemService.getCartItemsByUserId(userId);
		return ResponseEntity.ok(cartItems);
	}

	@GetMapping("/cartitemid/{cartItemId}")
	public ResponseEntity<CartItem> getCartItemById(@PathVariable int cartItemId) {
		return new ResponseEntity<CartItem>(cartItemService.getCartItemById(cartItemId), HttpStatus.OK);
	}

}
