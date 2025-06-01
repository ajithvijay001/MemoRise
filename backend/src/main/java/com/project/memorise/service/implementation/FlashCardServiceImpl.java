package com.project.memorise.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.memorise.model.Decks;
import com.project.memorise.model.FlashCards;
import com.project.memorise.repository.DeckRepository;
import com.project.memorise.repository.FlashCardRepository;
import com.project.memorise.security.CustomUserDetails;
import com.project.memorise.service.DecksService;
import com.project.memorise.service.FlashCardService;
import com.project.memorise.service.SequenceGeneratorService;

@Service
public class FlashCardServiceImpl implements FlashCardService {
	
	@Autowired
	FlashCardRepository flashCardRepo;
	
	@Autowired
	DeckRepository deckRepo;
	
	@Autowired
	DecksService deckService;
	
	@Autowired
    private SequenceGeneratorService sequenceGeneratorService;
	
	private int getCurrentUserId() {
		return ((CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
	}

	private Decks verifyDeckOwnership(int deckId) {
	    return Optional.ofNullable(deckRepo.findByUserIdAndDeckId(getCurrentUserId(), deckId))
	        .orElseThrow(() -> new RuntimeException("Deck not found or doesn't belong to the user"));
	}

	
	@Override
	public List<FlashCards> findFlashCardByDeckId(int deckId) {
		verifyDeckOwnership(deckId);
		return flashCardRepo.findByDeckId(deckId);
		
	}

	@Override
	public FlashCards addFlashCard(int deckId, FlashCards flashCard) {
		verifyDeckOwnership(deckId);
		flashCard.setCardId(sequenceGeneratorService.getNextSequence("card_seq"));
		return flashCardRepo.save(flashCard);
	}

	@Override
	public FlashCards editFlashCard(int deckId, FlashCards flashCard) {
		verifyDeckOwnership(flashCard.getDeckId());
		return flashCardRepo.save(flashCard);
	}

	@Override
	public FlashCards deleteCard(int deckId, int cardId) {
		verifyDeckOwnership(deckId);
		 FlashCards card = flashCardRepo.findByDeckIdAndCardId(deckId, cardId)
			        .orElseThrow(() -> new RuntimeException("Card does not exist or does not belong to the user"));

		flashCardRepo.delete(card);
		return card;
		
	}

	@Override
	public FlashCards fetchFlashCardByCardIdAndDeckId(int cardId, int deckId) {
		verifyDeckOwnership(deckId);
		return flashCardRepo.findByCardIdAndDeckId(cardId, deckId);
	}

	@Override
	public String addCardToLiked(int deckId, int cardId) {
		
		verifyDeckOwnership(deckId);
		
		Optional<FlashCards> optionalCard = flashCardRepo.findByDeckIdAndCardId(deckId, cardId);

	    if (optionalCard.isPresent()) {
	        FlashCards card = optionalCard.get();
	        card.setLiked(!card.isLiked());
	        flashCardRepo.save(card); 
	        return card.isLiked() ? "Card added to Liked cards" : "Card removed from Liked cards";
	    } else {
	        throw new RuntimeException("Flashcard not found for deckId: " + deckId + ", cardId: " + cardId);
	    }
	}

	@Override
	public List<FlashCards> searchCards(String text) {
		return flashCardRepo.searchCards(Pattern.quote(text.trim()), getCurrentUserId());
	}

	@Override
	public String toggleCardRead(int deckId, int cardId) {
		verifyDeckOwnership(deckId);
		Optional<FlashCards> optionalCard = flashCardRepo.findByDeckIdAndCardId(deckId, cardId);
		if(optionalCard.isPresent()) {
			FlashCards card = optionalCard.get();
			card.setRead(!card.isRead());
			FlashCards savedCardId = flashCardRepo.save(card);
			System.out.println("Saved card read status: " + savedCardId.isRead());
			
			if(savedCardId.getCardId() !=0) {
				if(deckService.updateDeckReadStatus(deckId)) {
					return "Card marked as read. You have completed the deck. Great job!";
				}
			}
			return "Card marked as " + (card.isRead() ? "read. Great job learning it!" : "unread.");
		}
		return "Card not found!";
	}

}
