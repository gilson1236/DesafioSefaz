package br.org.desafiosefaz.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.desafiosefaz.jdbc.dao.JdbcUsuarioDao;
import br.org.desafiosefaz.modelo.Usuario;

public class RemoveUsuarioLogica implements Logica{
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int id = (int) Long.parseLong(request.getParameter("id"));
		
		Usuario usuario = new Usuario();
		usuario.setId(id);
		
		JdbcUsuarioDao dao = new JdbcUsuarioDao();
		dao.remove(usuario);
		
		System.out.println("Excluindo usuario...");
		
		return "mvc?logica=ListaUsuariosLogica";
	}
}
