package com.project.memorise.service;

import java.util.List;


import com.project.memorise.model.FlashCards;

public interface FlashCardService {
	
	List<FlashCards> findFlashCardByDeckId( String deckId);
	
	FlashCards addFlashCard(FlashCards flashCards);

	FlashCards editFlashCard(FlashCards flashCards);

	FlashCards deleteCard(int deckId, int id);

	FlashCards fetchFlashCardById(String deckId, String id);

	String addCardToLiked(int deckId, int cardId);

	List<FlashCards> searchCards(String text);
}
