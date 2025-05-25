package com.project.memorise.service.implementation;

import java.util.List;

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
	public String deleteCard(String id) {
		if(!flashCardRepo.existsById(id)) {
//			handle exception
		}
		flashCardRepo.deleteById(id);
		return "Deletion Successful";
	}

	@Override
	public FlashCards fetchFlashCardById(String deckId, String id) {
		return flashCardRepo.findByIdAndDeckId(id, deckId);
		
	}

}
