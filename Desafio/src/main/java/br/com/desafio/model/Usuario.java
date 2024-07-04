package br.com.desafio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="Usuario_id")
	private List<Telefone> telefones;
	
	public Usuario() {}
	
	public Usuario(String nome, String email, String senha, List<Telefone> telefone){
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefones = telefone;
	}	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	
}
