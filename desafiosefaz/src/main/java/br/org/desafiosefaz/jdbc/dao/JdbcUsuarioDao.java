package br.org.desafiosefaz.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.org.desafiosefaz.jdbc.ConnectionFactory;
import br.org.desafiosefaz.modelo.Telefone;
import br.org.desafiosefaz.modelo.Usuario;

public class JdbcUsuarioDao implements UsuarioDao {

	private Connection connection;

	public JdbcUsuarioDao() {

		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Usuario> lista() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from usuario");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nome"));

				usuarios.add(usuario);
			}

			rs.close();
			stmt.close();
			return usuarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Usuario usuario) {
		String sql = "insert into usuario " + "(nome,email,senha)" + " values (?,?,?)";
		List<Telefone> telefones;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			telefones = usuario.getTelefones();

			stmt.execute();

			sql = "insert into telefone " + "(ddd,numero,tipo,userId)" + " values (?,?,?,?)";
			stmt = connection.prepareStatement(sql);

			for (Telefone tel : telefones) {
				stmt.setInt(1, tel.getDdd());
				stmt.setString(2, tel.getNumero());
				stmt.setString(3, tel.getTipo());
				stmt.setInt(4, usuario.getId());
			}
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Usuario usuario) {
		String sql = "update usuario set nome=?, email=?, " + "senha=? where id=?";
		List<Telefone> telefones;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setLong(4, usuario.getId());
			telefones = usuario.getTelefones();

			stmt.execute();

			sql = "update telefone set ddd=?, numero=?, " + "tipo=? where userId=?";
			stmt = connection.prepareStatement(sql);

			for (Telefone tel : telefones) {
				stmt.setInt(1, tel.getDdd());
				stmt.setString(2, tel.getNumero());
				stmt.setString(3, tel.getTipo());
				stmt.setInt(4, usuario.getId());
			}

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Usuario usuario) {
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from usuario where id=?");
			stmt.setLong(1, usuario.getId());
			stmt.execute();
			stmt = connection.prepareStatement("delete from telefone where userId=?");
			stmt.setLong(1, usuario.getId());
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Usuario buscaPeloID(Usuario usuario) {
		String sql = "select * from usuario where id =?";

		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				return buscaUsuario(resultSet);
			}
			resultSet.close();
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public Usuario buscaParaLogin(Usuario usuario) {
		String sql = "select * from usuario where email = ?";
		Usuario usuariodoSql = new Usuario();
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()) {
				usuariodoSql.setEmail(resultSet.getString("email"));
				usuariodoSql.setSenha(resultSet.getString("senha"));
			}
			resultSet.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return usuariodoSql;
	}

	@SuppressWarnings("null")
	private Usuario buscaUsuario(ResultSet resultSet) throws SQLException {
		Usuario usuario = new Usuario();
		List<Telefone> telefones = null;
		Telefone telefone = null;
		usuario.setId(resultSet.getInt("id"));
		usuario.setNome(resultSet.getString("nome"));
		usuario.setEmail(resultSet.getString("email"));
		usuario.setSenha(resultSet.getString("senha"));
		String sql = "select * from telefone where userId =?";

		PreparedStatement stmt = this.connection.prepareStatement(sql);
		stmt.setLong(5, usuario.getId());
		ResultSet resultSetTelefone = stmt.executeQuery();

		while(resultSetTelefone.next()) {
			telefone.setDdd(resultSetTelefone.getInt("ddd"));
			telefone.setNumero(resultSetTelefone.getString("numero"));
			telefone.setTipo(resultSetTelefone.getString("tipo"));
			telefones.add(telefone);
		}
		usuario.setTelefones(telefones);

		return usuario;
	}
}
