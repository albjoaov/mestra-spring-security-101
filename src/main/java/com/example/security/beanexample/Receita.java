package com.example.security.beanexample;

import java.util.List;

public class Receita {

	private String nome;
	private List<String> ingredientes;

	public Receita (String nome, List<String> ingredientes) {
		this.nome = nome;
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString () {
		return "Receita{" +
				"nome='" + nome + '\'' +
				", ingredientes=" + ingredientes +
				'}';
	}
}
