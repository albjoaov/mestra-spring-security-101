package com.example.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity (securedEnabled = true)
public class ResourceServerSecurityConfig extends WebSecurityConfigurerAdapter {

	// Auth config

	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/user").permitAll()
				.anyRequest().authenticated()
				.and()
				.oauth2ResourceServer().opaqueToken();
	}

}
