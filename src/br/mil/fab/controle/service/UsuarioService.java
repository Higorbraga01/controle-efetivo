package br.mil.fab.controle.service;

import javax.ejb.Local;

import br.mil.fab.controle.entities.Usuario;

@Local
public interface UsuarioService {
	
	public Usuario getUsuario(String nomeUsuario, String senha);
	public boolean inserirUsuario(Usuario usuario);
	public boolean deletarUsuario(Usuario usuario);
}
