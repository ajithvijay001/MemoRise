package com.project.memorise.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.memorise.model.Decks;


@Repository
public interface DeckRepository extends MongoRepository<Decks, String>{

	List<Decks> findByUserId(String userId);

//	List<Decks> findAllByUserName(String username);

	List<Decks> findAllByUserId(int userId);
}
