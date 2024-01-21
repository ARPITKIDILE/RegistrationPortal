package com.explore.web.app.util;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.explore.web.app.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm; 

/***
 * This class contains utility functions required for authentication
 * @author arpit
 *
 */
@Component 
public class AuthUtil implements Serializable {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	public static final long TOKEN_VALIDITY = Constants.TWENTY_FOUR_HOURS; @Value("{secret}")
	   private String jwtSecret; 
	
	   public String generateJwtToken(Users user) { 
	      Map<String, Object> claims = new HashMap<>(); 
	      return Jwts.builder().setClaims(claims).setSubject(String.valueOf(user.getId()))
	         .setIssuedAt(new Date(System.currentTimeMillis())) 
	         .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000)) 
	         .signWith(SignatureAlgorithm.HS512, jwtSecret).compact(); 
	   } 
	   public Boolean validateJwtToken(String token) { 
	      Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	      Boolean isTokenExpired = claims.getExpiration().before(new Date()); 
	      return ( !isTokenExpired); 
	   } 
	   public String getIdFromToken(String token) {
	      final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); 
	      return claims.getSubject(); 
	   } 
}