package br.mil.fab.controle.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.mil.fab.controle.entities.Inspecao;


@Stateless
public class InspecaoDAO extends GenericDAO<Inspecao> {

	public InspecaoDAO(){
		super(Inspecao.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Inspecao> obterInspecoes(){
		StringBuilder sql = new StringBuilder(" SELECT * FROM INSPECAO ORDER BY DT_REALIZACAO DESC ");
		Query query = this.em.createNativeQuery(sql.toString(), Inspecao.class);
		return query.getResultList();
	}
	
	public Inspecao obterInspecaoById(Long cdInspecao) {
		Inspecao inspecao = null;
		if (cdInspecao != null) {
			try {
				StringBuilder sql = new StringBuilder(" SELECT * FROM INSPECAO IS WHERE IS.CD_INSPECAO = ? ");
				Query query = this.em.createNativeQuery(sql.toString(), Inspecao.class);
				int i = 0;
				query.setParameter(++i, cdInspecao);
				inspecao = (Inspecao) query.getSingleResult();
			} catch (NoResultException e) {
				inspecao = null;
			}
		}
		return inspecao;
	}

	@SuppressWarnings("unchecked")
	public List<Inspecao> buscarInspecoesPessoa(Long cdPessoa) {
		List<Inspecao> pessoa = null;
		StringBuilder sql = new StringBuilder(" SELECT * FROM INSPECAO  WHERE CD_PESSOA = ? ORDER BY DT_REALIZACAO DESC ");
		Query query = this.em.createNativeQuery(sql.toString(), Inspecao.class);
		int i = 0;
		query.setParameter(++i, cdPessoa);
		pessoa = (List<Inspecao>) query.getResultList();
		return pessoa;
	}

}