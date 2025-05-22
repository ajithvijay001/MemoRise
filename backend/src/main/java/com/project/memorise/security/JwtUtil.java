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

	private static final String secretKey = "wApA4VDDm4XyJddx6lY2fNPkE4HFCT7q";
//	private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
	public String generateToken(UserDetails user) {
//		Claims claim =(Claims)Jwts.claims();
		return Jwts.builder().subject(user.getUsername())
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+60*60*30))
		.signWith(getKey())
		.compact();
	}
	
	public SecretKey getKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
//	public Boolean validateToken(String token, UserDetails user) {
//		return extractUserName(token).equals(user.getUsername());
//		
//	}
//	
//	public String extractUserName(String token) {
//		return Jwts.parser().verifyWith(getKey())
//		.build()
//		.parseSignedClaims(token)
//		.getPayload().getSubject();
//	}
	
//	public void validToken(String token, UserDetails users) {
//		
//	}
	
//	public UserCredentials parseToken(String token) {
//		Claims jwtBody = Jwts.parser().setSigningKey(secretKey).
//		return new UserCredentials(username, grantedAuthorities, email, password);
//	}
}
