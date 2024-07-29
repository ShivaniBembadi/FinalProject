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
import org.springframework.web.bind.annotation.RestController;

import com.handloomstore.entity.User;
import com.handloomstore.service.UserServiceImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")

@RestController // is controller which provides different end points to access the services
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	// Register
	// http://localhost:8084/register
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {

		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}

	// Login
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user) {

		return new ResponseEntity<User>(userService.loginUser(user), HttpStatus.OK);

	}

	// Get All Customer
	@GetMapping("/all")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	// Get customer id
	@GetMapping("{id}")
	public User getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	
	
	
	// Update Customer
	@PutMapping("updateuser/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user, userId), HttpStatus.OK);
	}

	// Delete Customer
	@DeleteMapping("deleteuser/{id}") // @DeleteMapping("/admin/users/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int userId) {
		userService.deleteUser(userId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

	// Changing password
	@PostMapping("changepassword/{uid}/{newpassword}")
	public User changeUserPassword(@PathVariable("uid") int uid, @PathVariable("newpassword") String newpassword) {
		// return customerService.getCustomerByEmail(customer);
		User u = userService.getUserById(uid);
		u.setPassword(newpassword);
		userService.updateUser(u, uid);
		return u;
	}
	
}
