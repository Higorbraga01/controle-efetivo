package br.mil.fab.controle.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.mil.fab.controle.entities.Pessoa;
import br.mil.fab.controle.service.PessoaService;

@SuppressWarnings("deprecation")
@ManagedBean(name = "cadPessoaMB")
@ViewScoped
public class CadastrarPessoaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private transient PessoaService pessoaService;

	// listas

	// Objetos
	private Pessoa pessoa;

	@PostConstruct
	public void inicializar() {
		inicializarEntidades();

	}

	public void inicializarEntidades() {
		pessoa = new Pessoa();

	}

	public String salvar() {
		try {
			pessoa.setNmPessoa(pessoa.getNmPessoa().toUpperCase());
			pessoa.setNmGuerra(pessoa.getNmGuerra().toUpperCase());
			pessoa.setCargoSetor(pessoa.getCargoSetor().toUpperCase());
			pessoa.setNmEndereco(pessoa.getNmEndereco().toUpperCase());
			pessoa.setNrTituloEstado(pessoa.getNrTituloEstado().toUpperCase());
			pessoa.setNrTituloMunicipio(pessoa.getNrTituloMunicipio().toUpperCase());
			pessoa.setReligiao(pessoa.getReligiao().toUpperCase());
			pessoa.setTpCor(pessoa.getTpCor().toUpperCase());
			pessoa.setTpOlhos(pessoa.getTpOlhos().toUpperCase());
			pessoa.setTpCabelo(pessoa.getTpCabelo().toUpperCase());
			pessoa.setInAtivo("A");
			pessoaService.salvarPessoa(pessoa);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "PESSOA", "Cadastrada com sucesso!!!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Cadastro não realizado!!!"));
			e.printStackTrace();
		}
		return "pessoas?faces-redirect=true";
	}

	public String redireciona() {
		return "pessoas?faces-redirect=true";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
