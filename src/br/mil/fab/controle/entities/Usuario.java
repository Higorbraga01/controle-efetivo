package br.mil.fab.controle.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "USUARIO")
@NamedQuery(name = "Usuario.findAll", query = "SELECT p FROM Usuario p")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 @Id
     @Column(name="id", nullable=false, unique=true)
     private int id;
      
     @Column(name="userName", nullable=false, unique=true)
     private String nomeUsuario;
      
     @Column(name="password", nullable=false, unique=false)
     private String senha;
 
     @Column(name="lastAccess", unique=true)
     @Temporal(TemporalType.DATE)
     private Date ultimoAcesso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	
}
