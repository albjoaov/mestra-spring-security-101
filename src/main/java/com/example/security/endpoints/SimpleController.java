package com.example.security.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

	@GetMapping("/user")
	public String ola() {
		return "Olá, mundo";
	}

	@GetMapping("/admin")
	public String protectedOla() {
		return "Olá, ADMIN";
	}

}
