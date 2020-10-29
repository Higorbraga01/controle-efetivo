package br.mil.fab.controle.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.mil.fab.controle.dao.UsuarioDAO;
import br.mil.fab.controle.entities.Usuario;
import br.mil.fab.controle.service.UsuarioService;

@Stateless(name = "UsuarioImpl", mappedName = "portal-Model-UsuarioImpl")
public class UsuarioImpl implements UsuarioService {

	@EJB
	private transient UsuarioDAO usuarioDao;

	@Override
	public boolean inserirUsuario(Usuario usuario) {
		try {
			usuarioDao.save(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean deletarUsuario(Usuario usuario) {
		try {
			usuarioDao.save(usuario);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Usuario getUsuario(String nomeUsuario, String senha) {
		return usuarioDao.getUsuario(nomeUsuario, senha);
	}

}
