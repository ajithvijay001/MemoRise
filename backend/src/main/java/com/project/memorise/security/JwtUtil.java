package com.project.memorise.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final String secretKey = "aKeyforProject01";
//	private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	public String generateToken(UserDetails user) {
		Claims claim =(Claims)Jwts.claims();
		return Jwts.builder().subject(user.getUsername())
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+60*60*30))
		.signWith(getKey())
		.compact();
	}
	
	public Key getKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}
