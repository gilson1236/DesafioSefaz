package br.org.desafiosefaz.mvc.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.desafiosefaz.jdbc.dao.JdbcUsuarioDao;
import br.org.desafiosefaz.modelo.Usuario;

public class ListaUsuarioLogica implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Usuario> usuarios = new JdbcUsuarioDao().lista();

		request.setAttribute("usuarios", usuarios);

		return "/WEB-INF/jsp/lista-contatos-elegante.jsp";
	}
}
