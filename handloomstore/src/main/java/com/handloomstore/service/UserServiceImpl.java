package com.handloomstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.handloomstore.entity.User;
import com.handloomstore.repository.UserRepository;


@Service
public class UserServiceImpl  {
	@Autowired
	private UserRepository userRepository;

	
	public User  saveUser(User user) {

		return userRepository.save(user);
		
	}

	public User loginUser(User user) {
		return this.userRepository.findByEmailIdAndPassword(user.emailId,user.password).get();
	
	}

	
	public User updateUser(User user, int userId) {
	
	
		
		User existingUser=userRepository.findById(userId).get();	
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setDistrict(user.getDistrict());
		existingUser.setPhoneNumber(user.getPhoneNumber());
		existingUser.setPlotNo(user.getPlotNo());
		existingUser.setStreetName(user.getStreetName());
		existingUser.setCity(user.getCity());
		existingUser.setState(user.getState());
		existingUser.setZipCode(user.getZipCode());
		existingUser.setEmailId(user.getEmailId());
		existingUser.setPassword(user.getPassword());
		return userRepository.save(existingUser);
		//return existingUser;
		}

	public List<User> getAllUser() {
		return userRepository.findAll();
		
	}


	public void deleteUser(int userId) {
		userRepository.findById(userId).get();
		userRepository.deleteById(userId);
		
	}

	public User getUserById(int userId) {
		return userRepository.findById(userId).get();
		
	}

}
