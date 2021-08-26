package com.example.security.endpoints;

import com.example.security.dto.UserDto;
import com.example.security.service.TokenService;
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

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public AuthController (AuthenticationManager authenticationManager,
	                       TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<String> auth(@Valid @RequestBody UserDto userDto) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = userDto.toAuthToken();

		try {
			Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			String token = this.tokenService.generateJwt(authenticate);
			String fullToken = "Bearer " + token;
			return ResponseEntity.ok(fullToken);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
