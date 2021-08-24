package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BeanConfigs {

	@Bean
	public Receita receita() {
		List<String> ingredientesList = List.of("ovo", "farinha", "chocolate");
		Receita bolo = new Receita("bolo", ingredientesList);
		System.out.println(bolo.toString());
		return bolo;
	}
}
