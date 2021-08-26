package com.example.security.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(AuthenticationException.class)
//	public ResponseEntity<HashMap<String, String>> handleBadCredentials(AuthenticationException exception) {
//		System.out.println(exception);
//		HashMap<String, String> map = new HashMap<>();
//		map.put("Mensagem", "Mensagem customizada");
//		map.put("Mensagem da exceção", exception.getLocalizedMessage());
//		return ResponseEntity.status(401).body(map);
//	}

	// Exemplo de Yallamy
//	@ExceptionHandler(value = AuthenticationException.class)
//	public ModelAndView handleSigadException(AuthenticationException ex) {
//		System.out.println("Log: " + ex);
//		ModelAndView model = new ModelAndView();
//		model.setStatus(HttpStatus.UNAUTHORIZED);
//		model.addObject("error", ex.getMessage());
//		return model;
//	}
}
