package com.project.memorise.controller;

import java.util.List;

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

import com.project.memorise.model.FlashCards;
import com.project.memorise.service.FlashCardService;

import static org.springframework.http.ResponseEntity.ok;

@RestController

@RequestMapping("/api")
public class FlashCardController {

	@Autowired
	FlashCardService flashCardSerive;
	
	@GetMapping("/deck/{deckId}/flashCards")
	public ResponseEntity<List<FlashCards>> getFlashCards(@PathVariable String deckId){
		return ok(flashCardSerive.findFlashCardByDeckId(deckId));
	}
	
	@GetMapping("/deck/{deckId}/flashCards/{id}")
	public ResponseEntity<FlashCards> getFlashCardById(@PathVariable String deckId, @PathVariable String id){
		return ok(flashCardSerive.fetchFlashCardById(deckId, id));
	}
	@PostMapping("/create-card")
	public ResponseEntity<FlashCards> addFlashCards(@RequestBody FlashCards flashCards)
	{
		return ok(flashCardSerive.addFlashCard(flashCards));
	}
	
	@PutMapping("/edit-card")
	public ResponseEntity<FlashCards> editFlashCard(@RequestBody FlashCards flashCards){
		return ok(flashCardSerive.editFlashCard(flashCards));
	}
	
	@DeleteMapping("/delete-card")
	public ResponseEntity<String> deleteFlashCard(@PathVariable String id){
		return ok(flashCardSerive.deleteCard(id));
	}
	
	@PostMapping("/like")
	public ResponseEntity<String> addCardToFav(@PathVariable int cardId){
		return ok(flashCardSerive.addCardToFav(cardId));
	}
	
//	@GetMapping("/search")
//	public ResponseEntity<FlashCards> searchCards(@RequestParam String text){
//		
//		return ok(flashCardSerive.searchCards(text));
//	}

}
