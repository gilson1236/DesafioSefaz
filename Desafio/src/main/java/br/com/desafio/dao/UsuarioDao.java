package br.com.desafio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import br.com.desafio.model.Usuario;

public class UsuarioDao {
	
	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("UsuarioPU");

	@Transactional
	public void create(Usuario usuario) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("entrou aqui");
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<Usuario> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Usuario> usuarios = entityManager.createQuery("From Usuario",Usuario.class).getResultList();
		return usuarios;
	}
	
	public void update(Usuario usuario) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void delete(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Usuario usuario = entityManager.find(Usuario.class, id);
		
		if(usuario != null) {
			entityManager.remove(usuario);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public Usuario findById(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Usuario usuario = entityManager.find(Usuario.class, id);
		
		return usuario;
	}
}
