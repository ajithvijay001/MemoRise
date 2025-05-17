package com.project.memorise.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.memorise.model.Decks;


@Repository
public interface DeckRepository extends MongoRepository<Decks, String>{

}
