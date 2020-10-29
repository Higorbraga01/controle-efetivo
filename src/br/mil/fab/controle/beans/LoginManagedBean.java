package br.mil.fab.controle.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.mil.fab.controle.entities.Usuario;
import br.mil.fab.controle.filter.SessionContext;
import br.mil.fab.controle.service.UsuarioService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginManagedBean implements Serializable {

	private static final long serialVersionUID = -7697371074981231641L;

	@EJB
	private UsuarioService usuarioService;

	private Usuario usuario = new Usuario();

	public String envia() {

		usuario = usuarioService.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha incorretos!", "Erro no Login!"));
			FacesContext.getCurrentInstance().validationFailed();
			return null;
		} else {
			SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
			return "index?faces-redirect=true";
		}

	}
	
    public String logOff() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "login?faces-redirect=true";
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}