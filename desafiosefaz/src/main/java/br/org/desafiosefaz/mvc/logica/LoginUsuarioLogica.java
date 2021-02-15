package br.org.desafiosefaz.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.desafiosefaz.jdbc.dao.JdbcUsuarioDao;
import br.org.desafiosefaz.modelo.Usuario;

public class LoginUsuarioLogica implements Logica {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JdbcUsuarioDao dao = new JdbcUsuarioDao();
		Usuario usuario = new Usuario();
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		Usuario usuarioRetorno = dao.buscaParaLogin(usuario);
		System.out.println("login logica");
		if (usuario.getEmail() != null && usuario.getSenha().equals(usuarioRetorno.getSenha())) {
			return "WEB-INF/lista-usuarios.jsp";
		} else {
			return "WEB-INF/login.jsp";
		}
	}

}
