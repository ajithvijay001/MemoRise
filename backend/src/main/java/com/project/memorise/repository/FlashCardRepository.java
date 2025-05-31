package com.project.memorise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.memorise.model.FlashCards;
@Repository
public interface FlashCardRepository extends MongoRepository<FlashCards, String> {

	List<FlashCards> findByDeckId(String deckId);
	
	 FlashCards findByIdAndDeckId(String id, String deckId);

	Optional<FlashCards> findByDeckIdAndCardId(int deckId, int cardId);

	@Query("{ '$or': [ " +
		       "{ 'frontPage': { $regex: ?0, $options: 'i' } }, " +
		       "{ 'hint': { $regex: ?0, $options: 'i' } }, " +
		       "{ 'meaning': { $regex: ?0, $options: 'i' } }, " +
		       "{ 'onyomi': { $regex: ?0, $options: 'i' } }, " +
		       "{ 'kunyomi': { $regex: ?0, $options: 'i' } } ] }")
		List<FlashCards> searchCards(String text);

	boolean existsByCardId(int id);

	FlashCards deleteByDeckIdAndCardId(int deckId, int cardId);
}
