package br.mil.fab.controle.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="INSPECAO")
@NamedQuery(name="Inspecao.findAll", query="SELECT p FROM Inspecao p")
public class Inspecao implements Serializable{

	private static final long serialVersionUID = -4287049995140184623L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CD_INSPECAO")
	private Long cdInspecao;
	
	@Column(name = "TP_INSPECAO")
	private String tpInspecao;
	
	
	@Column(name = "TP_RESULTADO")
	private String tpResultado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_REALIZACAO")
	private Date dtRealizacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_VALIDADE")
	private Date dtValidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_PESSOA")
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getCdInspecao() {
		return cdInspecao;
	}

	public void setCdInspecao(Long cdInspecao) {
		this.cdInspecao = cdInspecao;
	}

	public String getTpInspecao() {
		return tpInspecao;
	}

	public void setTpInspecao(String tpInspecao) {
		this.tpInspecao = tpInspecao;
	}

	public String getTpResultado() {
		return tpResultado;
	}

	public void setTpResultado(String tpResultado) {
		this.tpResultado = tpResultado;
	}

	public Date getDtRealizacao() {
		return dtRealizacao;
	}

	public void setDtRealizacao(Date dtRealizacao) {
		this.dtRealizacao = dtRealizacao;
	}

	public Date getDtValidade() {
		return dtValidade;
	}

	public void setDtValidade(Date dtValidade) {
		this.dtValidade = dtValidade;
	}
	
	
}