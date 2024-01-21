package com.explore.web.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.explore.web.app.entity.Users;
import com.explore.web.app.service.UsersService;

/***
 * This will be the controller class for all API's
 * 
 * @author arpit
 *
 */

@RestController
@CrossOrigin
public class WebAppController {

	@Autowired 
	private UsersService userService;
	
	/**
	 * User Registration API
	 * @param user
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> register(@RequestBody Users user){
		return userService.register(user);
	}
	
	/**
	 * User Login API
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Users user){
		return userService.login(user);
	}
	
	/**
	 * User Profile API
	 * @param user
	 * @return
	 */
	@GetMapping("/details/{username}")
	public ResponseEntity<Map<String, Object>> details(@PathVariable String userName){
		return userService.details(userName);
	}
	
	/**
	 * Profile Editing API
	 * @param user
	 * @return
	 */
	@PutMapping("/edit")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Users user){
		return userService.update(user);
	}

}
