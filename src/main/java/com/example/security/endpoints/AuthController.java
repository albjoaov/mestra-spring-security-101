package com.example.security.endpoints;

import com.example.security.dto.UserDto;
import com.example.security.model.User;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Key;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Value("${jwt.expiration.local}")
	private String tempoExpiracaoMilis;

	@Value("${jwt.secret}")
	private String secret;

	private final AuthenticationManager authenticationManager;

	private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public AuthController (AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostMapping
	public ResponseEntity<String> auth(@Valid @RequestBody UserDto userDto) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = userDto.toAuthToken();

		try {
			Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String token = this.generateJwt(authenticate);
			String fullToken = "Bearer " + token;
			return ResponseEntity.ok(fullToken);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

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

		JwtParser build = Jwts.parserBuilder().setSigningKey(KEY).build();
		build.parseClaimsJws(stringJwt);

		return stringJwt;

	}

}
