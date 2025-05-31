package com.project.memorise.service;

import java.util.List;


import com.project.memorise.model.FlashCards;

public interface FlashCardService {
	
	List<FlashCards> findFlashCardByDeckId( int deckId);
	
	FlashCards addFlashCard(int deckId, FlashCards flashCards);

	FlashCards editFlashCard(int deckId, FlashCards flashCards);

	FlashCards deleteCard(int deckId, int id);

	FlashCards fetchFlashCardByCardIdAndDeckId(int deckId, int cardId);

	String addCardToLiked(int deckId, int cardId);

	List<FlashCards> searchCards(String text);
}
