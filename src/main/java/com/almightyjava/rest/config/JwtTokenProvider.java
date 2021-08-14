package com.almightyjava.rest.config;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.almightyjava.rest.domain.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider implements Serializable {

	private static final long serialVersionUID = 2569800841756370596L;

	@Value("${jwt.secret-key}")
	private String secretKey;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	private long validityInMilliseconds = 50 * 60 * 60 * 60; // 2 minute

	public String createToken(String username, Role role) {
		log.info("JwtTokenProvider : createToken");
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("auth", role.getRoleName());

		Date now = new Date();
		return Jwts.builder().setClaims(claims).setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + validityInMilliseconds))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	@Autowired
	private UserDetailsService userDetailsService;

	public Authentication getAuthentication(String username) {
		log.info("JwtTokenProvider : getAuthentication");
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),
				userDetails.getAuthorities());
	}

	public Claims getClaimsFromToken(String token) {
		log.info("JwtTokenProvider : getClaimsFromToken");
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

}