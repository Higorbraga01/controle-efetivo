package br.mil.fab.controle.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

import br.mil.fab.controle.entities.Inspecao;
import br.mil.fab.controle.entities.Pessoa;
import br.mil.fab.controle.service.InspecaoService;
import br.mil.fab.controle.service.PessoaService;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@ManagedBean(name = "pessoaBean")
@ViewScoped
@Getter
@Setter
public class PessoaBean {

	// EJBs
	@EJB
	PessoaService pessoaService;

	@EJB
	InspecaoService inspecaoService;

	// ATRIBUTOS
	private List<Pessoa> pessoas;
	private List<Inspecao> inspecoes;
	private Pessoa pessoa = new Pessoa();
	private Pessoa pessoaRemocao = new Pessoa();
	private String nomeBusca;
	private Pessoa pessoaSelecionada = new Pessoa();
	private Inspecao inspecaoPessoa = new Inspecao();
	private Inspecao inspecaoRemocao = new Inspecao();
	private boolean edita = true;

	// CONSTRUTOR
	public PessoaBean() {
		super();
		System.out.println("Construtor de PessoaBean executado!");
	}

	// METODO INVOCADO APOS O CONSTRUTOR
	@PostConstruct
	private void init() {

		try {
			this.pessoas = this.pessoaService.obterPessoasInAtivo();
			if (null != (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("idPessoa"))) {
				String id = new String(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
						.get("idPessoa"));
				this.pessoaSelecionada = this.pessoaService.consultaPessoa(Long.valueOf(id));
				this.inspecoes = this.inspecaoService.obterInspecoesPessoa(Long.valueOf(id));
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Inspecao> recarregarListaInpsecaoPessoa() {
		try {
			this.inspecoes = this.inspecaoService.obterInspecoesPessoa(pessoaSelecionada.getCdPessoa());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inspecoes;
	}

	public boolean habilitaEdicao() {
		edita = false;
		return edita;
	}

	// Muda a orientação da pagina exportada em .PDF - verificar possibilidade de um
	// método para aumentar o tamanho das colunas.
	public void preProcessPDF(Object document) {
		Document doc = (Document) document;
		doc.setPageSize(PageSize.A4.rotate());
		if (!doc.isOpen()) {
			doc.open();
		}
	}

	public void buscarPessoas() {
		if (!nomeBusca.trim().isEmpty()) {
			pessoas = pessoaService.buscarByNome(nomeBusca);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Preencha o nome  que deseja buscar!"));
		}
	}

	public void recarregarDados() {
		this.nomeBusca = "";
		this.init();
	}

	public void alterarPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		RequestContext.getCurrentInstance().execute("PF('modalEdicaoPessoa').show();");
	}

	public String salvarAlteracaoPessoa() {
		try {
			pessoaService.editaPessoa(pessoaSelecionada);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados", "Salvos com sucesso!!"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pessoas";
	}

	public void removerPessoa(Pessoa pessoa) {
		this.pessoaRemocao = pessoa;
		RequestContext.getCurrentInstance().execute("PF('modalRemocaoPessoa').show();");
	}

	public String efetuarRemocaoPessoa() {
		try {
			pessoaRemocao.setInAtivo("N");
			this.pessoaService.editaPessoa(pessoaRemocao);
			this.nomeBusca = "";
			this.init();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa", "Removida com sucesso!!!"));	
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Não foi possivel remover, tente novamente mais tarde!"));
		}
		return "pessoa";
	}

	public void abrirModalCadInspecao() {
		RequestContext.getCurrentInstance().execute("PF('modalCadastroInspecao').show();");
	}

	public void salvarInspecao() {
		try {
			inspecaoPessoa.setPessoa(pessoaSelecionada);
			inspecaoService.salvarInspecao(inspecaoPessoa);
			inspecaoPessoa = new Inspecao();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Inspeção", "Cadastrada com sucesso!!!"));
			recarregarListaInpsecaoPessoa();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", "Não foi possivel salvar a Inspeção"));
			e.printStackTrace();
		}

	}

	public void efetuarRemocaoInspecao(Inspecao inspecao) {
		try {
			inspecaoRemocao = inspecao;
			inspecaoService.removerInspecao(inspecaoRemocao);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Inspeção", "Removida com sucesso!!"));
			recarregarListaInpsecaoPessoa();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!",
					"Não foi possivel remover a inspeção selecionada."));
			e.printStackTrace();
		}
	}

	public String redireciona() {
		return "pessoas?faces-redirect=true";
	}

	// GETTERS AND SETTERS
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getNomeBusca() {
		return nomeBusca;
	}

	public void setNomeBusca(String nomeBusca) {
		this.nomeBusca = nomeBusca;
	}

	public Pessoa getPessoaRemocao() {
		return pessoaRemocao;
	}

	public void setPessoaRemocao(Pessoa pessoaRemocao) {
		this.pessoaRemocao = pessoaRemocao;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public Inspecao getInspecaoPessoa() {
		return inspecaoPessoa;
	}

	public void setInspecaoPessoa(Inspecao inspecaoPessoa) {
		this.inspecaoPessoa = inspecaoPessoa;
	}

	public List<Inspecao> getInspecoes() {
		return inspecoes;
	}

	public void setInspecoes(List<Inspecao> inspecoes) {
		this.inspecoes = inspecoes;
	}

	public Inspecao getInspecaoRemocao() {
		return inspecaoRemocao;
	}

	public void setInspecaoRemocao(Inspecao inspecaoRemocao) {
		this.inspecaoRemocao = inspecaoRemocao;
	}

	public boolean isEdita() {
		return edita;
	}

	public void setEdita(boolean edita) {
		this.edita = edita;
	}

}
