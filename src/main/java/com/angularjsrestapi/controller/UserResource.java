package com.angularjsrestapi.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angularjsrestapi.model.User;
import com.angularjsrestapi.service.UserService;

@RestController
@RequestMapping("/api")
public class UserResource {

	private UserService userService;
	
	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUserByID(@RequestParam(value = "email") String email,@RequestParam(value = "password") String password){
		
		return userService.findByEmailAndPassword(email,password);
	}
	
	@RequestMapping(value = "user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
		
		try {
			User result = userService.save(user);

			return ResponseEntity.created(new URI("/api/user/" + result.getUserID())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
