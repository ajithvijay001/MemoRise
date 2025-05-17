package com.project.memorise.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.memorise.model.FlashCards;
@Repository
public interface FlashCardRepository extends MongoRepository<FlashCards, String> {

	List<FlashCards> findByDeckId(String deckId);

}
