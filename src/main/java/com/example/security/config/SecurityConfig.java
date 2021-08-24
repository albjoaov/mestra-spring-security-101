package com.example.security.config;

import com.example.security.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableGlobalMethodSecurity (prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthService authService;

	public SecurityConfig (AuthService authService) {
		this.authService = authService;
	}

	// Authentication config
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(encoder());
	}

	// Authorization config
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user").hasRole("USER")
				.anyRequest().authenticated()
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.httpBasic()
			.and()
				.formLogin()
			.and()
				.csrf().disable();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	


}
