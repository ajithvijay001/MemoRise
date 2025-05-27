package com.project.memorise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.memorise.model.Decks;
import com.project.memorise.model.Users;
import com.project.memorise.model.dto.UsersDetailsDto;
import com.project.memorise.service.DecksService;
import com.project.memorise.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DecksService deckService;
	
	@GetMapping("/users/me")
	public UsersDetailsDto userPage() {
		Optional user = userService.getUserDetails();
		return new UsersDetailsDto((Users)user.get());
	}
	
	@GetMapping("users/decks/{userId}")
	public ResponseEntity<List<Decks>> fetchAllDecks(@PathVariable int userId){
		return ok(deckService.fetchAllDecks(userId));
	}
	
}
