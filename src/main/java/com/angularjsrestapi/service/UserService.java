package com.angularjsrestapi.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angularjsrestapi.model.Note;
import com.angularjsrestapi.model.User;
import com.angularjsrestapi.repository.UserRepository;


@Service
public class UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findByEmailAndPassword(String email,String password){
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	public User save(User user) {
		if (user.getUserID() != null && userRepository.exists(user.getUserID())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return userRepository.save(user);
	}
	
}
