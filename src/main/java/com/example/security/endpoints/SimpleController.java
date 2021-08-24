package com.example.security.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

	@GetMapping("/")
	// Teste com ROLES
	public String ola() {
		return "Olá, mundo";
	}

}
