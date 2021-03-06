package com.example.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String senha;

	// Sempre dê preferencia ao FetchType.LAZY
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Perfil> perfis = new ArrayList<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities () {
		return this.perfis;
	}

	@Override
	public String getPassword () {
		return this.senha;
	}

	@Override
	public String getUsername () {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired () {
		return true;
	}

	@Override
	public boolean isAccountNonLocked () {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired () {
		return true;
	}

	@Override
	public boolean isEnabled () {
		return true;
	}

	public Long getId () {
		return id;
	}
}
