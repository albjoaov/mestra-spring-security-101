package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Authentication config
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("mesttra").password(encoder().encode("123")).roles("ADMIN")
			.and()
			.withUser("aluno").password(encoder().encode("123")).roles("USER");
	}

	// Authorization config
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.httpBasic()
				.and()
				.formLogin();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	


}
