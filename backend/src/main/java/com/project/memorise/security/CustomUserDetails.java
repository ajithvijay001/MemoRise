package com.project.memorise.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {
	
	private int userId;
	
	public CustomUserDetails(int userId, String username, String password,
            Collection<? extends GrantedAuthority> authorities) {
super(username, password, authorities);
this.userId = userId;
}
	
	public int getUserId() {
		return userId;
	}

}
