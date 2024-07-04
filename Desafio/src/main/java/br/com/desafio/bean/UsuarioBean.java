package br.com.desafio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.desafio.dao.UsuarioDao;
import br.com.desafio.model.Telefone;
import br.com.desafio.model.Usuario;

//@ManagedBean
@Named
//@ViewScoped
@javax.faces.view.ViewScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsuarioDao usuarioDao;
	private Usuario usuario = new Usuario();
	private Telefone telefone;
	private List<Usuario> usuarios;
	
	public UsuarioBean() {
		usuarioDao = new UsuarioDao();
		usuario = new Usuario();
		usuario.setTelefones(new ArrayList<>());
		setTelefone(new Telefone());
	}
	
	public void salvar() {
		
		System.out.println("entrou aqui");
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(usuario.getId() == null) {
			usuarioDao.create(usuario);
			
		} else {
			usuarioDao.update(usuario);
		}
		
		context.addMessage(null, new FacesMessage("Usuário salvo com sucesso!"));
		
		usuario = new Usuario();
		usuario.setTelefones(new ArrayList<>());
		usuarios = usuarioDao.findAll();
	}
	
	public void remover(Long id) {
		usuarioDao.delete(id);
		usuarios = usuarioDao.findAll();
	}
	
	public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = usuarioDao.findAll();
        }
        return usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String adicionaTelefone() {
    	if(usuario.getTelefones() == null){
    		usuario.setTelefones(new ArrayList<>());
    	}
    	usuario.getTelefones().add(new Telefone());
    	return "Adicionado";
    }
    
    public String removerTelefone(Telefone telefone) {
    	usuario.getTelefones().remove(telefone);
    	return "Removido";
    }

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
}
