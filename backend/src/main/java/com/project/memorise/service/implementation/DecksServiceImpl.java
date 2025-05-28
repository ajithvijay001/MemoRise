package com.project.memorise.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.memorise.model.Decks;
import com.project.memorise.model.Users;
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
	public String deleteDeck(String id) {
		if(!deckRepo.existsById(id)) {
			//Handle exception here
		}
		deckRepo.deleteById(id);
		return "Deletion Success";
	}

	@Override
	public String addToFav(int deckId) {
		
		Decks deck = deckRepo.findByDeckId(deckId);
		deck.setLiked(!deck.isLiked());
		deckRepo.save(deck);
		return "Deck added to Liked Decks";
	}

}
