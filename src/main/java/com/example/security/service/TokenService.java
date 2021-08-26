package com.example.security.service;

import com.example.security.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

	@Value ("${jwt.expiration.local}")
	private String tempoExpiracaoMilis;
	private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateJwt(Authentication authentication) {
		User usuarioLogado = (User) authentication.getPrincipal();

		Date now = new Date();
		Date tempoExpiracao = new Date(now.getTime() + Long.parseLong(tempoExpiracaoMilis));

		String stringJwt = Jwts.builder()
				.setIssuer("app-seguranca.mesttra.com")
				.setSubject(usuarioLogado.getId().toString())
				.setIssuedAt(now)
				.setExpiration(tempoExpiracao)
				.signWith(KEY)
				.claim("teste", true)
				.compact();

		this.isTokenValid(stringJwt);

		return stringJwt;

	}

	public boolean isTokenValid (String tokenWithoutBearer) {

		try {
			JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(KEY).build();
			jwtParser.parseClaimsJws(tokenWithoutBearer);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Long getSub (String tokenWithoutBearer) {
		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(KEY).build();
		Claims payload = jwtParser.parseClaimsJws(tokenWithoutBearer).getBody();

		String subject = payload.getSubject();
		return Long.parseLong(subject);
	}
}
