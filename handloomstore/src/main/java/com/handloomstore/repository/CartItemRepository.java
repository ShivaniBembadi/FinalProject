package com.handloomstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.handloomstore.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	List<CartItem> findByUserUserId(int userId);

	CartItem findByUserUserIdAndProductProductId(int userId, int productId);
	
	//CartItem getCartItemById(int cartItemId);
}
