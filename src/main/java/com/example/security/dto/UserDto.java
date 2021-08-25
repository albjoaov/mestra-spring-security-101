package com.example.security.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonIgnoreProperties (ignoreUnknown = true) // Vai evitar que a serialização do DTO quebre ao passarem campos desconhecidos
@JsonAutoDetect (fieldVisibility = JsonAutoDetect.Visibility.ANY) // Permito a serialização sem get e set
public class UserDto {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 8)
	private String password;

	public UsernamePasswordAuthenticationToken toAuthToken () {
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}
}
