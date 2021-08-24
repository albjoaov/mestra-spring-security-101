package com.example.security.service;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

	private final UserRepository userRepository;

	public AuthService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = this.userRepository.findByEmail(email);
		return optionalUser.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
}
