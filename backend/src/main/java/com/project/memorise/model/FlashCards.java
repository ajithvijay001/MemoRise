package com.project.memorise.model;

import java.util.List;

import org.springframework.data.annotation.Id;
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
	
	private List<String> onyomi;
	
	private List<String> kunyomi;
	
	@Field("deckId")
	private String deckId;
	
	private String frontPage;
	
	private String hint;
	
	private List<String> meaning;

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public List<String> getOnyomi() {
//		return onyomi;
//	}
//
//	public void setOnyomi(List<String> onyomi) {
//		this.onyomi = onyomi;
//	}
//
//	public List<String> getKunyomi() {
//		return kunyomi;
//	}
//
//	public void setKunyomi(List<String> kunyomi) {
//		this.kunyomi = kunyomi;
//	}
//
//	public String getDeckId() {
//		return deckId;
//	}
//
//	public void setDeckId(String deckId) {
//		this.deckId = deckId;
//	}
//
//	public String getFrontPage() {
//		return frontPage;
//	}
//
//	public void setFrontPage(String frontPage) {
//		this.frontPage = frontPage;
//	}
//
//	public String getHint() {
//		return hint;
//	}
//
//	public void setHint(String hint) {
//		this.hint = hint;
//	}
//
//	public List<String> getMeaning() {
//		return meaning;
//	}
//
//	public void setMeaning(List<String> meaning) {
//		this.meaning = meaning;
//	}

//	@Override
//	public String toString() {
//	    return "FlashCards{" +
//	            "id='" + id + '\'' +
//	            ", onyomi=" + onyomi +
//	            ", kunyomi=" + kunyomi +
//	            ", deckId='" + deckId + '\'' +
//	            ", frontPage='" + frontPage + '\'' +
//	            ", hint='" + hint + '\'' +
//	            ", meaning=" + meaning +
//	            '}';
//	}

}
