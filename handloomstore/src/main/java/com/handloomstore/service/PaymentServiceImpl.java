package com.handloomstore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handloomstore.entity.Order;
import com.handloomstore.entity.Payment;
import com.handloomstore.entity.User;
import com.handloomstore.repository.OrderRepository;
import com.handloomstore.repository.PaymentRepository;




@Service
public class PaymentServiceImpl {
	@Autowired
	private PaymentRepository paymentRepository;
		
		@Autowired
		private OrderRepository orderRepository;

		
		@Autowired
		private UserServiceImpl userService;
		
		@Autowired
		private OrderServiceImpl orderService;


	        public PaymentServiceImpl(PaymentRepository paymentRepository, ProductServiceImpl bookService,
				UserServiceImpl userService,OrderServiceImpl orderService) {
			super();
			this.paymentRepository = paymentRepository;
			
			this.userService = userService;
			this.orderService = orderService;

		}


			public Payment addPayment(Payment payment, int orderId,int userId) {
				// TODO Auto-generated method stub
	        	Order order = orderRepository.findById(orderId).get();
				System.out.println("****************"+order.getOrderId());
	        	payment.setOrderId(orderId);
				payment.setTotalPrice(order.getTotalPrice());
				payment.setPaidDate(LocalDate.now());
				payment.setPaidAmount(order.getTotalPrice());
				if (payment.getTotalPrice() == payment.getPaidAmount()) {
					order.setPaymentStatus("PAID");
					order.setOrderStatus("Delivered");
				} else {

					order.setPaymentStatus("NOT-PAID");
					order.setOrderStatus("payment pending");
				}
					  User user=userService.getUserById(userId);
				    	
				    	payment.setUser(user);
				    	
				    	
				    	     //return paymentRepository.save(payment);
				    	
				
				return paymentRepository.save(payment);
				
	        }
	       
			public List<Payment> getAllPayments() {
				return paymentRepository.findAll();
			}


			
			public Payment getPaymentById(int paymentId) {
				return paymentRepository.findById(paymentId).get();
			}

			


			
			public void deletePayment(int paymentId) {
				paymentRepository.findById(paymentId).get();
				paymentRepository.deleteById(paymentId);
				
				
			}


			
			public List<Payment> getAllPaymentsByUserId(int userId) {
				return paymentRepository.findByOrderId(userId);
			}
}
