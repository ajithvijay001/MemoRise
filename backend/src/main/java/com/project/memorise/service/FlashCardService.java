package com.project.memorise.service;

import java.util.List;


import com.project.memorise.model.FlashCards;

public interface FlashCardService {
	
	List<FlashCards> findFlashCardByDeckId( String deckId);
	
	FlashCards addFlashCard(FlashCards flashCards);

	FlashCards editFlashCard(FlashCards flashCards);

	String deleteCard(String id);

	FlashCards fetchFlashCardById(String deckId, String id);

	String addCardToFav(int cardId);

	List<FlashCards> searchCards(String text);
}
