package com.project.memorise.service;

import java.util.List;

import com.project.memorise.model.Decks;

public interface DecksService {

	List<Decks> fetchAllDecks();
	
	Decks createNewDeck(Decks deck);

	Decks editDeck(Decks deck);

	String deleteDeck(String id);
}
