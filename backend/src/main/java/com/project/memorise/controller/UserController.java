package com.project.memorise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.memorise.model.Users;
import com.project.memorise.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import static org.springframework.http.ResponseEntity.accepted;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	UserService userService;
	
//	@GetMapping("/")
//	public String userPage(HttpServletRequest http) {
//		return "Test Successful ";
//	}
	
	@PostMapping("/register")
	public ResponseEntity<String> addNewUser(@RequestBody Users user) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUsers(user)); 
	}
}
