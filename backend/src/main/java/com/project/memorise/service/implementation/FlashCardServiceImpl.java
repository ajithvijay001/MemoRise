package com.project.memorise.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.memorise.model.FlashCards;
import com.project.memorise.repository.FlashCardRepository;
import com.project.memorise.service.FlashCardService;
import com.project.memorise.service.SequenceGeneratorService;

@Service
public class FlashCardServiceImpl implements FlashCardService {
	
	@Autowired
	FlashCardRepository flashCardRepo;
	
	@Autowired
    private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public List<FlashCards> findFlashCardByDeckId(String deckId) {
		return flashCardRepo.findByDeckId(deckId);
		
	}

	@Override
	public FlashCards addFlashCard(FlashCards flashCards) {
		flashCards.setCardId(sequenceGeneratorService.getNextSequence("card_seq"));
		return flashCardRepo.save(flashCards);
	}

	@Override
	public FlashCards editFlashCard(FlashCards flashCards) {
		return flashCardRepo.save(flashCards);
	}

	@Override
	public FlashCards deleteCard(int deckId, int cardId) {
		Optional<FlashCards> cards =flashCardRepo.findByDeckIdAndCardId(deckId, cardId); 
		if(!cards.isPresent()) {
//			handle exception
			System.out.println("Exception occured....");
			return new FlashCards();
		}
		return flashCardRepo.deleteByDeckIdAndCardId(deckId,cardId);
		
	}

	@Override
	public FlashCards fetchFlashCardById(String deckId, String id) {
		return flashCardRepo.findByIdAndDeckId(id, deckId);
		
	}

	@Override
	public String addCardToLiked(int deckId, int cardId) {
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

		return flashCardRepo.searchCards(text);
	}

}
