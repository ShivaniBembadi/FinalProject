package com.handloomstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handloomstore.entity.User;




public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmailIdAndPassword(String emailId,String password);

	Optional<User> findByUserId(int userId);
}
