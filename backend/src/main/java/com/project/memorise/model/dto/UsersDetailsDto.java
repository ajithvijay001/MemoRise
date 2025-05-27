package com.project.memorise.model.dto;

import java.util.List;

import com.project.memorise.model.Decks;
import com.project.memorise.model.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDetailsDto {
	
	public UsersDetailsDto(Users user) {
	    this.userName = user.getUserName();
	    this.emailId = user.getEmailId();
	    this.userId = user.getUserId();
	    this.decks = user.getDecks();
	}

	
	private String userName;
	
	private String emailId;
	
	private int userId;
	
	private List<Decks> decks;

}
