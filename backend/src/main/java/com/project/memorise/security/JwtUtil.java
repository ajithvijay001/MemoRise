package com.project.memorise.security;

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

	public String generateToken(UserDetails userDetails) {
		CustomUserDetails  user = (CustomUserDetails) userDetails;
		return Jwts.builder()
		.subject(user.getUsername())
		.claim("userId", user.getUserId())
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis() + 1000 * 60 *30))
		.signWith(getKey())
		.compact();
	}
	
	public SecretKey getKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
	public Boolean validateToken(String token, UserDetails user) {
		return extractUserName(token).equals(user.getUsername());
	}
	
	public Integer extractUserId(String token) {
        return extractUserIdClaims(token).get("userId", Integer.class);
    }
	
	public String extractUserName(String token) {
		return Jwts.parser().verifyWith(getKey())
		.build()
		.parseSignedClaims(token)
		.getPayload().getSubject();
	}	
	
	private Claims extractUserIdClaims(String token) {
        return (Claims) Jwts.parser().verifyWith(getKey())
        		.build() 
        		.parseSignedClaims(token)
        		.getPayload().get("userId");
    }
	
}
