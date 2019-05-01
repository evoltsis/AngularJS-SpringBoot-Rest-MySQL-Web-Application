package com.angularjsrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.angularjsrestapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByEmailAndPassword(String email, String password);
	
}
