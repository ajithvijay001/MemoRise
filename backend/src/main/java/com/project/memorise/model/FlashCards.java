package com.project.memorise.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("FlashCards")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class FlashCards {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private int cardId;
	
	private List<String> onyomi;
	
	private List<String> kunyomi;
	
	@Field("deckId")
	private int deckId;
	
	private String frontPage;
	
	private String hint;
	
	private List<String> meaning;
	
	private boolean liked=false;
	
	private boolean read = false;


}
