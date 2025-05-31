package com.project.memorise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.memorise.model.Decks;
import com.project.memorise.service.DecksService;


import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

@RestController

@RequestMapping("/api/deck")
public class DeckController {

	@Autowired
	DecksService deckService;
    
	
	@PostMapping("/create-deck")
	public ResponseEntity<Decks> createNewDeck(@RequestBody Decks deck){
		return ok(deckService.createNewDeck(deck));
	}
	
	@PutMapping("/edit-deck")
	public ResponseEntity<Decks> editDeck(@RequestBody Decks deck){
		return ok(deckService.editDeck(deck));
	}
	
	@DeleteMapping("/delete-deck/{deckId}")
	public ResponseEntity<Decks> deleteDeck(@PathVariable int deckId)
	{
		return ok(deckService.deleteDeck(deckId));
	}
	
	@GetMapping("/like/{deckId}")
	public ResponseEntity<String> addDeckToLiked(@PathVariable int deckId){
		return ok(deckService.addDeckToLiked(deckId));
	}
	
	@GetMapping("/search-decks")
	public ResponseEntity<List<Decks>> searchDecks(@RequestParam String text){
		
		return ok(deckService.searchDecks(text));
	}
	
}
