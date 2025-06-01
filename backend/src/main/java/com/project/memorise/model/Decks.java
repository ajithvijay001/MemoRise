package com.project.memorise.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "Decks")
public class Decks {

	@Id
	private String id;
	
	@Indexed(unique = true)
	private int deckId;
	
	private String deckName;
	
	private int userId;
	
	private boolean liked = false;
	
	private boolean read = false;
	
}
