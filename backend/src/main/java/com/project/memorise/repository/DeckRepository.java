package com.project.memorise.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.memorise.model.Decks;


@Repository
public interface DeckRepository extends MongoRepository<Decks, String>{

	List<Decks> findByUserId(int userId);

//	List<Decks> findAllByUserName(String username);

	List<Decks> findAllByUserId(int userId);

	Decks findByDeckId(int deckId);

	@Query("{ '$or': [ " +		       
		       "{ 'deckName': { $regex: ?0, $options: i } } ] }")
	List<Decks> searchDecks(String text);
}
