package com.handloomstore.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.handloomstore.entity.CartItem;
import com.handloomstore.entity.Order;
import com.handloomstore.entity.TransactionDetails;
import com.handloomstore.entity.User;
import com.handloomstore.repository.CartItemRepository;
import com.handloomstore.repository.OrderRepository;
import com.razorpay.RazorpayClient;


	@Transactional
	@Service
public class OrderServiceImpl {
		private static final String ORDER_PLACED = "Placed";

	    private static final String KEY = "rzp_test_AXBzvN2fkD4ESK";
	    private static final String KEY_SECRET = "bsZmiVD7p1GMo6hAWiy4SHSH";
	    private static final String CURRENCY = "INR";

		@Autowired
		public OrderRepository orderRepository;

		@Autowired
		public ProductServiceImpl productService;

		@Autowired
		public CartItemServiceImpl cartService;

		@Autowired
		private UserServiceImpl userService;

		@Autowired
		private CartItemRepository c;

		public OrderServiceImpl(OrderRepository orderRepository, ProductServiceImpl productService, CartItemServiceImpl cartService,
				UserServiceImpl userService) {
			super();
			this.orderRepository = orderRepository;
			this.productService = productService;
			// this.cartService = cartService;
			this.userService = userService;
		}

	
		public Order addOrder(Order order, int userId, int cartId) {
			
			CartItem cart = cartService.getCartItemById(cartId);
			User user = userService.getUserById(userId);
			order.setTotalPrice(order.getMrpPrice() * cart.getQuantity());
			order.setPaymentStatus(order.getPaymentStatus());
			order.setOrderStatus(order.getOrderStatus());
			order.setOrderedDate(order.getOrderedDate());
			order.setMrpPrice(cart.getMrpPrice());
			order.setQuantity(cart.getQuantity());
			System.out.println(">>>>>" + cart.getQuantity());
			order.setUser(user);
			Order o = orderRepository.save(order);
			c.deleteById(cartId);
			return o;
		}

		
		public Order getOrderById(int orderId) {
			return orderRepository.findByOrderId(orderId).get();

		}

		
		public Order updateOrder(Order order, int orderId) {
			Order existingOrder = orderRepository.findById(orderId).get();
					
			existingOrder.setTotalPrice(order.getMrpPrice());
			existingOrder.setPaymentStatus(order.getPaymentStatus());
			existingOrder.setMrpPrice(order.getMrpPrice());
			existingOrder.setOrderStatus(order.getOrderStatus());
			existingOrder.setUser(order.getUser());
			existingOrder.setOrderedDate(order.getOrderedDate());
			orderRepository.save(existingOrder);
			return existingOrder;
		}

		
		public List<Order> getOrderByUserId(int userId) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String currentDate = sdf.format(date);
			String[] array = currentDate.split("/");
			int month = Integer.parseInt(array[0]);
			int day = Integer.parseInt(array[1]);
			int year = Integer.parseInt(array[2]);
			java.util.Date d = new java.util.Date(month, day, year);
			System.out.println(d);
			List<Order> orders = orderRepository.findByUserUserId(userId);
			System.out.println(orders);
			return orderRepository.findByUserUserId(userId);
		}

	
		public Order addOrderItem(Order order, int userId) {
			User user = userService.getUserById(userId);
			order.setTotalPrice(order.getTotalPrice());
			order.setPaymentStatus(order.getPaymentStatus());
			order.setOrderStatus(order.getOrderStatus());
			order.setOrderedDate(order.getOrderedDate());
			order.setUser(user);
			order.setProduct(order.getProduct());
			Order o = orderRepository.save(order);
			return o;
		}

		
		public void deleteOrder(int orderId) {
			orderRepository.findByOrderId(orderId).get();
			orderRepository.deleteById(orderId);

			
		}

		
		public TransactionDetails createTransaction(double amount) {
			try {

	            JSONObject jsonObject = new JSONObject();
	            jsonObject.put("amount", (amount * 100) );
	            jsonObject.put("currency", CURRENCY);

	            RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);

	            com.razorpay.Order order = razorpayClient.orders.create(jsonObject);

	            TransactionDetails transactionDetails =  prepareTransactionDetails(order);
	            return transactionDetails;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return null;
	    }

	    private TransactionDetails prepareTransactionDetails(com.razorpay.Order order) {
	        String orderId = order.get("id");
	        String currency = order.get("currency");
	        Integer amount = order.get("amount");

	        TransactionDetails transactionDetails = new TransactionDetails(orderId, currency, amount, KEY);
	        return transactionDetails;
	    }

		
		public List<Order> getAllOrders() {
		
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				java.util.Date date = new java.util.Date();
				String currentDate = sdf.format(date);
				String[] array = currentDate.split("/");
				int month = Integer.parseInt(array[0]);
				int day = Integer.parseInt(array[1]);
				int year = Integer.parseInt(array[2]);
				java.util.Date d = new java.util.Date(month, day, year);
				System.out.println(d);
				List<Order> orders = orderRepository.findAll();
				System.out.println(orders);
				return orderRepository.findAll();
			}

			
		
		

}
