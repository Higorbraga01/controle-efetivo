package br.mil.fab.controle.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_PESSOA")
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = -4287049995140184623L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CD_PESSOA")
	private Long cdPessoa;
	
	@Column(name = "NR_ANTIGUIDADE")
	private Integer nrAntiguidade;
	
	@Column(name="NM_PESSOA")
	private String nmPessoa;
	
	@Column(name="NM_GUERRA")
	private String nmGuerra;
	
	@Column(name = "SG_GRAD")
	private String graduacao;
	
	@Column(name = "SG_QUADRO")
	private String quadro;
	
	@Column(name = "ST_RESERVA", length = 5)
	private String stReserva;
	
	@Column(name = "SG_ESPECIALIDADE")
	private String especialidade;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DT_APRESENTACAO")
	private Date dtApresentacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DT_PRACA")
	private Date dtPraca;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DT_ULTIMA_PROMOCAO")
	private Date dtUltPromo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DT_NASCIMENTO")
	private Date dtNascimento;
	
	@Column(name = "NR_IDENTIDADE")
	private String nrIdentidade;
	
	@Column(name = "NR_CPF", length = 15)
	private String nrCpf;
	
	@Column(name = "NR_SARAM")
	private String nrSaram;
	
	@Column(name = "NM_CARGO")
	private String cargoSetor;
	
	@Column(name = "NM_SETOR")
	private String nmSetor;
	
	@Column(name = "NM_DIVISAO")
	private String nmDivisao;
	
	@Column(name = "NM_ENDERECO")
	private String nmEndereco;
	
	@Column(name = "NR_TELEFONE")
	private String nrTelefone;
	
	@Column(name = "NR_TITULO_ELEITOR")
	private String nrTitulo;
	
	@Column(name = "NR_TITULO_ZONA")
	private String nrTituloZona;
	
	@Column(name = "NR_TITULO_SECAO")
	private String nrTituloSecao;
	
	@Column(name = "NR_TITULO_ESTADO")
	private String nrTituloEstado;
	
	@Column(name = "NR_TITULO_MUNICIPIO")
	private String nrTituloMunicipio;
	
	@Column(name = "HONRAS_MILITARES", length = 3)
	private String honrasMilitares;
	
	@Column(name = "IN_ATIVO", length = 1)
	private String inAtivo;
	
	@Column(name = "ESCOLARIDADE")
	private String escolaridade;
	
	@Column(name = "RELIGIAO")
	private String religiao;
	
	@Column(name = "TP_RH")
	private String tpRh;
	
	@Column(name = "TP_COR")
	private String tpCor;
	
	@Column(name = "TP_OLHOS")
	private String tpOlhos;
	
	@Column(name = "TP_CABELO")
	private String tpCabelo;
	
	@Column(name = "NR_CAMISA")
	private Integer nrCamisa;
	
	@Column(name = "NR_CINTURA")
	private Integer nrCintura;
	
	@Column(name = "NR_CALCADO")
	private Integer nrCalcado;
	
	@Column(name = "NR_COBETURA")
	private Integer nrCobertura;
	
	@Column(name = "NR_LUVA")
	private Integer nrLuva;
	
	@ElementCollection
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<Inspecao> inspecao;
	

	public Long getCdPessoa() {
		return cdPessoa;
	}

	public void setCdPessoa(Long cdPessoa) {
		this.cdPessoa = cdPessoa;
	}

	public String getNmPessoa() {
		return nmPessoa;
	}

	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}

	public String getNmGuerra() {
		return nmGuerra;
	}

	public void setNmGuerra(String nmGuerra) {
		this.nmGuerra = nmGuerra;
	}

	public Date getDtApresentacao() {
		return dtApresentacao;
	}

	public void setDtApresentacao(Date dtApresentacao) {
		this.dtApresentacao = dtApresentacao;
	}

	public Date getDtPraca() {
		return dtPraca;
	}

	public void setDtPraca(Date dtPraca) {
		this.dtPraca = dtPraca;
	}

	public Date getDtUltPromo() {
		return dtUltPromo;
	}

	public void setDtUltPromo(Date dtUltPromo) {
		this.dtUltPromo = dtUltPromo;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNrIdentidade() {
		return nrIdentidade;
	}

	public void setNrIdentidade(String nrIdentidade) {
		this.nrIdentidade = nrIdentidade;
	}

	public String getNrCpf() {
		return nrCpf;
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	public String getNrSaram() {
		return nrSaram;
	}

	public void setNrSaram(String nrSaram) {
		this.nrSaram = nrSaram;
	}

	public String getNmSetor() {
		return nmSetor;
	}

	public void setNmSetor(String nmSetor) {
		this.nmSetor = nmSetor;
	}

	public String getNmEndereco() {
		return nmEndereco;
	}

	public void setNmEndereco(String nmEndereco) {
		this.nmEndereco = nmEndereco;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public String getNrTitulo() {
		return nrTitulo;
	}

	public void setNrTitulo(String nrTitulo) {
		this.nrTitulo = nrTitulo;
	}

	public String getNrTituloZona() {
		return nrTituloZona;
	}

	public void setNrTituloZona(String nrTituloZona) {
		this.nrTituloZona = nrTituloZona;
	}

	public String getNrTituloSecao() {
		return nrTituloSecao;
	}

	public void setNrTituloSecao(String nrTituloSecao) {
		this.nrTituloSecao = nrTituloSecao;
	}

	public String getNrTituloEstado() {
		return nrTituloEstado;
	}

	public void setNrTituloEstado(String nrTituloEstado) {
		this.nrTituloEstado = nrTituloEstado;
	}

	public String getNrTituloMunicipio() {
		return nrTituloMunicipio;
	}

	public void setNrTituloMunicipio(String nrTituloMunicipio) {
		this.nrTituloMunicipio = nrTituloMunicipio;
	}

	public String getHonrasMilitares() {
		return honrasMilitares;
	}

	public void setHonrasMilitares(String honrasMilitares) {
		this.honrasMilitares = honrasMilitares;
	}

	public String getReligiao() {
		return religiao;
	}

	public void setReligiao(String religiao) {
		this.religiao = religiao;
	}

	public String getTpRh() {
		return tpRh;
	}

	public void setTpRh(String tpRh) {
		this.tpRh = tpRh;
	}

	public String getTpCor() {
		return tpCor;
	}

	public void setTpCor(String tpCor) {
		this.tpCor = tpCor;
	}

	public String getTpOlhos() {
		return tpOlhos;
	}

	public void setTpOlhos(String tpOlhos) {
		this.tpOlhos = tpOlhos;
	}

	public String getTpCabelo() {
		return tpCabelo;
	}

	public void setTpCabelo(String tpCabelo) {
		this.tpCabelo = tpCabelo;
	}

	public Integer getNrCamisa() {
		return nrCamisa;
	}

	public void setNrCamisa(Integer nrCamisa) {
		this.nrCamisa = nrCamisa;
	}

	public Integer getNrCintura() {
		return nrCintura;
	}

	public void setNrCintura(Integer nrCintura) {
		this.nrCintura = nrCintura;
	}

	public Integer getNrCalcado() {
		return nrCalcado;
	}

	public void setNrCalcado(Integer nrCalcado) {
		this.nrCalcado = nrCalcado;
	}

	public Integer getNrCobertura() {
		return nrCobertura;
	}

	public void setNrCobertura(Integer nrCobertura) {
		this.nrCobertura = nrCobertura;
	}

	public Integer getNrLuva() {
		return nrLuva;
	}

	public void setNrLuva(Integer nrLuva) {
		this.nrLuva = nrLuva;
	}

	public String getInAtivo() {
		return inAtivo;
	}

	public void setInAtivo(String inAtivo) {
		this.inAtivo = inAtivo;
	}

	public Integer getNrAntiguidade() {
		return nrAntiguidade;
	}

	public void setNrAntiguidade(Integer nrAntiguidade) {
		this.nrAntiguidade = nrAntiguidade;
	}

	public String getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getQuadro() {
		return quadro;
	}

	public void setQuadro(String quadro) {
		this.quadro = quadro;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getStReserva() {
		return stReserva;
	}

	public void setStReserva(String stReserva) {
		this.stReserva = stReserva;
	}

	public String getNmDivisao() {
		return nmDivisao;
	}

	public void setNmDivisao(String nmDivisao) {
		this.nmDivisao = nmDivisao;
	}

	public String getCargoSetor() {
		return cargoSetor;
	}

	public void setCargoSetor(String cargoSetor) {
		this.cargoSetor = cargoSetor;
	}

	public List<Inspecao> getInspecao() {
		return inspecao;
	}

	public void setInspecao(List<Inspecao> inspecao) {
		this.inspecao = inspecao;
	}

}