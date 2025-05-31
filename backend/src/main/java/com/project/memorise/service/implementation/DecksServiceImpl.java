package com.project.memorise.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.memorise.model.Decks;
import com.project.memorise.repository.DeckRepository;
import com.project.memorise.repository.UserRepository;
import com.project.memorise.service.DecksService;
import com.project.memorise.service.SequenceGeneratorService;


@Service
public class DecksServiceImpl implements DecksService{

	@Autowired
	DeckRepository deckRepo;
	
	@Autowired 
	UserRepository userRepo;
	
	@Autowired
    private SequenceGeneratorService sequenceGeneratorService;
	
	@Override
	public List<Decks> fetchAllDecks(int userId) {
		return deckRepo.findAllByUserId(userId);
	}

	@Override
	public Decks createNewDeck(Decks deck) {
		deck.setDeckId(sequenceGeneratorService.getNextSequence("deck_seq"));
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		deck.setUserId(userRepo.findByUserName(user.getUsername()).get().getUserId());
		return deckRepo.save(deck);
	}

	@Override
	public Decks editDeck(Decks deck) {
		return deckRepo.save(deck);
	}

	@Override
	public Decks deleteDeck(int deckId) {
		Optional<Decks> optionalDeck = deckRepo.findByDeckId(deckId); 
		if(!optionalDeck.isPresent()) {
			//Handle exception here
			return new Decks();
		}
		return deckRepo.deleteByDeckId(deckId);
		
	}

	@Override
	public String addDeckToLiked(int deckId) {
		
		Optional<Decks> optionalCard = deckRepo.findByDeckId(deckId);

	    if (optionalCard.isPresent()) {
	    	Decks deck = optionalCard.get();
	        deck.setLiked(!deck.isLiked());
	        deckRepo.save(deck); // Don't forget to save the change
	        return deck.isLiked() ? "Card added to Liked cards" : "Card removed from Liked cards";
	    } else {
	        throw new RuntimeException("Deck not found for deckId: " + deckId );
	    }
	}

	@Override
	public List<Decks> searchDecks(String text) {
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return deckRepo.searchDecks(text, userRepo.findByUserName(user.getUsername()).get().getUserId());
	}

}
