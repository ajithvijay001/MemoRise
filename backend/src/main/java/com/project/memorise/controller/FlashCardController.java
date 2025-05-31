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
	public ResponseEntity<List<FlashCards>> getFlashCards(@PathVariable int deckId){
		return ok(flashCardSerive.findFlashCardByDeckId(deckId));
	}
	
	@GetMapping("/deck/{deckId}/flashCards/{cardId}")
	public ResponseEntity<FlashCards> getFlashCardById(@PathVariable int deckId, @PathVariable int cardId){
		return ok(flashCardSerive.fetchFlashCardByCardIdAndDeckId(cardId, deckId));
	}
	@PostMapping("/deck/{deckId}/flashcards")
	public ResponseEntity<FlashCards> addFlashCards(@PathVariable int deckId, @RequestBody FlashCards flashCards)
	{
		flashCards.setDeckId(deckId);
		return ok(flashCardSerive.addFlashCard(deckId, flashCards));
	}
	
	@PutMapping("/deck/{deckId}/flashcards")
	public ResponseEntity<FlashCards> editFlashCard(@PathVariable int deckId, @RequestBody FlashCards flashCards){
		flashCards.setDeckId(deckId);
		return ok(flashCardSerive.editFlashCard(deckId,flashCards));
	}
	
	@DeleteMapping("deck/{deckId}/flashcards/{cardId}")
	public ResponseEntity<FlashCards> deleteFlashCard(@PathVariable int deckId, @PathVariable int cardId){
		return ok(flashCardSerive.deleteCard(deckId, cardId));
	}
	
	@PutMapping("/deck/{id}/like/{cardId}")
	public ResponseEntity<String> addCardToLiked(@PathVariable int cardId, @PathVariable int id){
		return ok(flashCardSerive.addCardToLiked(id, cardId));
	}
	
	@GetMapping("/flashcards/search")
	public ResponseEntity<List<FlashCards>> searchCards(@RequestParam String text){
		return ok(flashCardSerive.searchCards(text));
	}

}
