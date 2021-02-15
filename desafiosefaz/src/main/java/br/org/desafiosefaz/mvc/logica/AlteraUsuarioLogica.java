package br.org.desafiosefaz.mvc.logica;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.org.desafiosefaz.jdbc.dao.JdbcUsuarioDao;
import br.org.desafiosefaz.modelo.Telefone;
import br.org.desafiosefaz.modelo.Usuario;

public class AlteraUsuarioLogica implements Logica {
	@SuppressWarnings("null")
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = new Usuario();
		long id = Long.parseLong(request.getParameter("id"));
		List<Telefone> telefones = new ArrayList<Telefone>();
		Telefone telefoneASerAlterado = null;
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		String arquivo = request.getParameter("telefones");
		usuario.setId((int)id);
		String[] items = arquivo.split(";");

		for (int i = 0; i < items.length; i += 3) {
			telefoneASerAlterado.setDdd(Integer.parseInt(items[i]));
			telefoneASerAlterado.setNumero(items[i + 1]);
			telefoneASerAlterado.setTipo(items[i + 2]);
			telefones.add(telefoneASerAlterado);
		}
		usuario.setTelefones(telefones);

		System.out.println("Adicionando Usuario..." + usuario.getNome());

		JdbcUsuarioDao dao = new JdbcUsuarioDao();
		dao.altera(usuario);
		
		System.out.println("Alterando usuario..." + usuario.getNome());
		return "mvc?logica=ListaUsuariosLogica";
	

	}

}
