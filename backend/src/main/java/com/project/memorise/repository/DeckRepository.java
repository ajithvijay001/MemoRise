package com.project.memorise.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.memorise.model.Decks;


@Repository
public interface DeckRepository extends MongoRepository<Decks, String>{

	List<Decks> findByUserId(int userId);

//	List<Decks> findAllByUserName(String username);

	List<Decks> findAllByUserId(int userId);

	Optional<Decks> findByDeckId(int deckId);

	@Query("{ '$and': [ " +
		       "{ 'userId': ?1 }, " +
		       "{ '$or': [ " +
		         "{ 'deckName': { $regex: ?0, $options: 'i' } } " +
		       "] } " +
		     "] }")
	List<Decks> searchDecks(String text, int userId);

	Decks deleteByDeckId(int deckId);

	Decks findByUserIdAndDeckId(int userId, int deckId);
}
