package br.com.repositorio;

import br.com.entidades.Pessoa;

public interface IDaoPessoa {
	
	public Pessoa consultarUsuario(String login, String senha);
}
