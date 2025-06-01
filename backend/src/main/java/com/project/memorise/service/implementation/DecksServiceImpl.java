package com.project.memorise.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.memorise.model.Decks;
import com.project.memorise.repository.DeckRepository;
import com.project.memorise.repository.FlashCardRepository;
import com.project.memorise.repository.UserRepository;
import com.project.memorise.security.CustomUserDetails;
import com.project.memorise.service.DecksService;
import com.project.memorise.service.SequenceGeneratorService;


@Service
public class DecksServiceImpl implements DecksService{

	@Autowired
	DeckRepository deckRepo;
	
	@Autowired 
	UserRepository userRepo;
	
	@Autowired
	FlashCardRepository flashCardRepo;
	
	@Autowired
    private SequenceGeneratorService sequenceGeneratorService;
	
	private int getCurrentUserId() {
		return ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
	}
	
	@Override
	public List<Decks> fetchAllDecks() {
		return deckRepo.findAllByUserId(getCurrentUserId());
	}

	@Override
	public Decks createNewDeck(Decks deck) {
		deck.setDeckId(sequenceGeneratorService.getNextSequence("deck_seq"));
		deck.setUserId(getCurrentUserId());
		return deckRepo.save(deck);
	}

	@Override
	public Decks editDeck(Decks deck) {
		Decks existingDeck = deckRepo.findByUserIdAndDeckId(getCurrentUserId(), deck.getDeckId());
		if(existingDeck != null) {
			existingDeck.setDeckName(deck.getDeckName());
			existingDeck.setLiked(deck.isLiked());
			return deckRepo.save(existingDeck);
		}
		else throw new RuntimeException("Deck not found for the user");
	}

	@Override
	public Decks deleteDeck(int deckId) {
		Decks deck= deckRepo.findByUserIdAndDeckId(getCurrentUserId(), deckId);
		if(deck == null) {
		//Handle custom exception here
			throw new RuntimeException("Deck Not Found or does not belong to the user");
		}
		deckRepo.deleteByDeckId(deckId);
		return deck;
	}

	@Override
	public String addDeckToLiked(int deckId) {

		Decks deck= deckRepo.findByUserIdAndDeckId(getCurrentUserId(), deckId);
		
	    if (deck != null) {
	        deck.setLiked(!deck.isLiked());
	        deckRepo.save(deck);
	        return deck.isLiked() ? "Deck added to Liked cards" : "Deck removed from Liked cards";
	    } else {
	        throw new RuntimeException("Deck not found for deckId: " + deckId );
	    }
	}

	@Override
	public List<Decks> searchDecks(String text) {
		return deckRepo.searchDecks(Pattern.quote(text.trim()), getCurrentUserId());
	}

	@Override
	public boolean updateDeckReadStatus(int deckId) {
		
		boolean allRead = flashCardRepo.areAllFlashcardsLearnt(deckId);
		
		Decks deck= deckRepo.findByUserIdAndDeckId(getCurrentUserId(), deckId);
		 if (deck != null) {
			 deck.setRead(allRead);
			 deckRepo.save(deck);
		    } else {
		        throw new RuntimeException("Deck not found for deckId or does not belong to the user" );
		    }
		 return allRead;
	}

}
