package com.project.memorise.repository;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

public class FlashCardsRepositoryCustomImpl implements FlashCardsRepositoryCustom {

	public final MongoTemplate mongoTemplate;
	
	public FlashCardsRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	@Override
	public boolean areAllFlashcardsLearnt(Integer deckId) {
		
		MatchOperation matchDeck = Aggregation.match(Criteria.where("deckId").is(deckId));
		
		GroupOperation groupByDeck = Aggregation.group("deckId")
	            .min("read").as("allLearnt");
		
		Aggregation aggregation = Aggregation.newAggregation(matchDeck, groupByDeck);
        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "FlashCards", Document.class);

        Document result = results.getUniqueMappedResult();
        
		return result!=null && result.getBoolean("allLearnt", false);
	}

}
