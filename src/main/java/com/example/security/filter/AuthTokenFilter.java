package com.example.security.filter;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import com.example.security.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthTokenFilter extends OncePerRequestFilter {

	private final TokenService tokenService;
	private final UserRepository userRepository;

	public AuthTokenFilter (TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal (HttpServletRequest request,
	                                 HttpServletResponse response,
	                                 FilterChain filterChain) throws ServletException, IOException {

		String tokenWithoutBearer = getTokenWithoutBearer(request);

		boolean isValid = tokenService.isTokenValid(tokenWithoutBearer);

		if (isValid) {
			doAuthUser(tokenWithoutBearer);
		}

		filterChain.doFilter(request, response);
	}

	private void doAuthUser (String tokenWithoutBearer) {
		Long userId = this.tokenService.getSub(tokenWithoutBearer);
		Optional<User> optionalUser = this.userRepository.findByIdWithJoinFetch(userId);
//		Optional<User> optionalUser = this.userRepository.findById(userId);

//		if (optionalUser.isPresent()) {
//			User user = optionalUser.get();
//		} else {
//			throw new RuntimeException();
//		}

		User user = optionalUser.orElseThrow(RuntimeException::new);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null);
//		authentication.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getTokenWithoutBearer (HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isBlank() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7);
	}
}
