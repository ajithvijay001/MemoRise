package com.project.memorise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.stereotype.Service;

import com.project.memorise.model.CustomSequences;

@Service
public class SequenceGeneratorService {

	@Autowired
	private MongoOperations mongo;
	
	public int getNextSequence(String seqName)
    {
       CustomSequences count = mongo.findAndModify(query(where("_id").is(seqName)),
    		   new Update().inc("seq", 1),options().returnNew(true).upsert(true),
    		   CustomSequences.class);
	return (int) count.getSeq();
    }
}
