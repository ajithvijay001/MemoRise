package com.project.memorise.model;

import java.util.List;

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

@Document(collection="Users")
public class Users {
	
	@Id
	private String id; 
	
	@Indexed(unique = true)
	private int userId;
	
	private String userName;
	
	private String password;
	
	private String emailId;
	
	private String role;
	
	private List<Decks> decks;

}
