package br.org.desafiosefaz.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.desafiosefaz.jdbc.dao.JdbcUsuarioDao;
import br.org.desafiosefaz.modelo.Usuario;

public class SelecionaUsuarioLogica implements Logica{
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (request.getParameter("id") != null) {

			int codigo = (int) Long.parseLong(request.getParameter("id"));

			Usuario u = new Usuario();

			u.setId(codigo);

			Usuario buscaPeloID = new JdbcUsuarioDao().buscaPeloID(u);

			request.setAttribute("usuario", buscaPeloID); // Guarda o usuario na requisição

			return "WEB-INF/altera-usuario.jsp";
		} else {

			return "WEB-INF/adiciona-usuario.jsp";
		}
	}
}
