package br.mil.fab.controle.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.mil.fab.controle.entities.Usuario;

@Stateless
public class UsuarioDAO extends GenericDAO<Usuario> {

	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario getUsuario(String nomeUsuario, String senha) {

		Usuario usuario = null;
		try {									  
			StringBuilder sql = new StringBuilder("SELECT * FROM USUARIO WHERE userName = :name and password = :senha ");
			Query query = this.em.createNativeQuery(sql.toString(), Usuario.class);
			query.setParameter("name", nomeUsuario);
			query.setParameter("senha", senha);
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			usuario = null;
		}
		return usuario;
	}

}
