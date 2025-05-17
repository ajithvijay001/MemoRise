package com.project.memorise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.memorise.model.FlashCards;
import com.project.memorise.repository.FlashCardRepository;

import static org.springframework.http.ResponseEntity.ok;

@RestController

@RequestMapping("/api")
public class FlashCardController {

	@Autowired
	FlashCardRepository flashCardRepo;
	
	@GetMapping("/deck/{deckId}/flashCards")
	public List<FlashCards> getFlashCards(@PathVariable String deckId){
		List<FlashCards> response = flashCardRepo.findByDeckId(deckId);// getByDeckId(deckId);
		System.out.println("Response= "+ response.get(0));
		return response;
	}
	
	@PostMapping("/addFlashCards")
	public ResponseEntity<FlashCards> addFlashCards(@RequestBody FlashCards flashCards)
	{
		return ok(flashCardRepo.save(flashCards));
	}

}
