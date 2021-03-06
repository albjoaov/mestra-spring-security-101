package com.example.security.endpoints;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Map;

@RestController
public class SimpleController {

	@GetMapping("/user")
	public String ola() {
		return "Olá, mundo";
	}

	@GetMapping("/admin")
//	@Secured ("ROLE_ADMIN")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	@RolesAllowed({"ROLE_ADMIN"})
	public String protectedOla() {
		return "Olá, ADMIN";
	}

	@PostMapping("/")
	public String post(@RequestBody Map<String, String> map) {
		String valor = map.get("chave");
		return "Olá, o valor da chave é " + valor;
	}

}
