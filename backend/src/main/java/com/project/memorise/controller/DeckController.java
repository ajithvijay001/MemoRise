package com.project.memorise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.memorise.model.Decks;
import com.project.memorise.service.DecksService;


import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

@RestController

@RequestMapping("/api")
public class DeckController {
	
	@Autowired
	DecksService deckService;
	
	@GetMapping("/fetch-decks/{userId}")
	public ResponseEntity<List<Decks>> fetchAllDecks(@PathVariable int userId){
		return ok(deckService.fetchAllDecks(userId));
	}
	
	@PostMapping("/create-deck")
	public ResponseEntity<Decks> createNewDeck(@RequestBody Decks deck){
		return ok(deckService.createNewDeck(deck));
	}
	
	@PutMapping("/edit-deck")
	public ResponseEntity<Decks> editDeck(@RequestBody Decks deck){
		return ok(deckService.editDeck(deck));
	}
	
	@DeleteMapping("/delete-deck")
	public ResponseEntity<String> deleteDeck(@PathVariable String id)
	{
		return ok(deckService.deleteDeck(id));
	}
	
}
