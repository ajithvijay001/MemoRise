package com.project.memorise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.memorise.model.Users;
import com.project.memorise.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String userPage(HttpServletRequest http) {
		System.out.println("Hi there" + http.getSession().getId());
		return "Test Successful " + http.getSession().getId();
	}
	
	@PostMapping("/add-user")
	public String addNewUser(@RequestBody Users user) {
		
		return userService.addUsers(user);
	}
}
