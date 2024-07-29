package com.handloomstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handloomstore.entity.Order;



@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer>{
	public List<Order> findByUserUserId(int userId);
	public void deleteByOrderId(int orderId);
	public Optional<Order> findByOrderId(int orderId);
	

}
