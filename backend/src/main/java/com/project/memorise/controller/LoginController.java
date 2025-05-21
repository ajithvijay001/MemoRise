package com.project.memorise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.memorise.model.Users;
import com.project.memorise.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		System.out.print("INSIDE THE LOGIN CONTROLLER");
		try {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		UserDetails userDetails =(UserDetails)authentication.getPrincipal();
		String token = jwtUtil.generateToken(userDetails);
		return token;
		}
		catch (Exception e) {
			return "failed";
		}
		
	}
}
