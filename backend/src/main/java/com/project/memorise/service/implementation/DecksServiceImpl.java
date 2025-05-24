package com.project.memorise.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.memorise.model.Decks;
import com.project.memorise.repository.DeckRepository;
import com.project.memorise.service.DecksService;

@Service
public class DecksServiceImpl implements DecksService{

	@Autowired
	DeckRepository deckRepo;
	
	@Override
	public List<Decks> fetchAllDecks() {
		return deckRepo.findAll();
	}

	@Override
	public Decks createNewDeck(Decks deck) {
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

}
