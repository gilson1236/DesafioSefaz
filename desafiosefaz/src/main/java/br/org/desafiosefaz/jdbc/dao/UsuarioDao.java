package br.org.desafiosefaz.jdbc.dao;

import java.util.List;

import br.org.desafiosefaz.modelo.Usuario;

public interface UsuarioDao {
	List<Usuario> lista();
	void adiciona(Usuario u);
	void altera(Usuario u);
	void remove(Usuario u);
}
