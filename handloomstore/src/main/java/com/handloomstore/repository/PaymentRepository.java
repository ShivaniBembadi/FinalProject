package com.handloomstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handloomstore.entity.Payment;





@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	public List<Payment> findByOrderId(long orderId);

}
