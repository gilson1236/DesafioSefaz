package br.org.desafiosefaz.mvc.logica;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.desafiosefaz.jdbc.dao.JdbcUsuarioDao;
import br.org.desafiosefaz.modelo.Telefone;
import br.org.desafiosefaz.modelo.Usuario;

public class AdicionaUsuarioLogica implements Logica{

	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = new Usuario();
		List<Telefone> telefones = new ArrayList<Telefone>();
		Telefone telefoneASerAdicionado = new Telefone();
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		String arquivo = request.getParameter("telefone");
		System.out.println(request.getParameter("telefone"));
        String[] items = arquivo.split("-");
        System.out.println(items[0]);
        telefoneASerAdicionado.setNumero(items[1]);
        telefoneASerAdicionado.setTipo(request.getParameter("tel"));
        telefoneASerAdicionado.setDdd(Integer.valueOf(items[0]));
        usuario.setTelefones(telefones);
		
		JdbcUsuarioDao dao = new JdbcUsuarioDao();
		dao.adiciona(usuario);
		
		System.out.println("Adicionando Usuario..." + usuario.getNome());
		
		return "mvc?logica=ListaUsuarioLogica";

	}

}
