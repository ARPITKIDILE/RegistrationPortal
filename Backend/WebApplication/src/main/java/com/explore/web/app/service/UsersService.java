package com.explore.web.app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.explore.web.app.entity.Users;
import com.explore.web.app.repository.UsersRepository;
import com.explore.web.app.util.AuthUtil;
import com.explore.web.app.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersService {
	@Autowired 
	private UsersRepository repository;
	
	@Autowired
	AuthUtil auth;
	
	public List<Users> getAllUsers(){
		return repository.findAll();
	}
	
	public ResponseEntity<Map<String, Object>> register(Users user) {
		Map<String, Object> response = new HashMap<>();
		try {
			Users userData = repository.findByEmail(user.getEmail());
			if (userData == null) {
				LocalDateTime currentTimestamp = LocalDateTime.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				user.setLoggedDate(currentTimestamp.format(formatter));
				
				user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
				;
				Users savedUser = repository.save(user);
				response.put("user", savedUser);
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}
			response.put("error", "User with this email already exists!!");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			log.error("Error in register service");
			response.put("error", Constants.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Map<String, Object>> login(Users user) {
		Map<String, Object> response = new HashMap<>();
		try {
			Users userData = repository.findByUserName(user.getUserName());
			if (userData == null || !BCrypt.checkpw(user.getPassword(),userData.getPassword())) {
				response.put("error", "Please enter correct Credentials!!");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
			response.put("user", userData);
			response.put("token", auth.generateJwtToken(userData));
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			log.error("Error in login service");
			response.put("error", Constants.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Map<String, Object>> details(String userName) {
		Map<String, Object> response = new HashMap<>();
		try {
			Users userData = repository.findByUserName(userName);
			response.put("user", userData);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			log.error("Error in details service");
			response.put("error", Constants.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Map<String, Object>> update(Users user) {
		Map<String, Object> response = new HashMap<>();

		try {
			Users userData = repository.findByUserName(user.getUserName());
		
			if(user.getEmail()!=null) userData.setEmail(user.getEmail());
			
			Users updatedUserData = repository.save(userData);
			
			response.put("user", updatedUserData);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			log.error("Error in update service");
			response.put("error", Constants.SOMETHING_WENT_WRONG);
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
}
