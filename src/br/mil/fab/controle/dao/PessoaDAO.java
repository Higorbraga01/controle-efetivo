package br.mil.fab.controle.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.mil.fab.controle.entities.Pessoa;


@Stateless
public class PessoaDAO extends GenericDAO<Pessoa> {

	public PessoaDAO(){
		super(Pessoa.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> obterPessoasInAtivo(){
		StringBuilder sql = new StringBuilder(" SELECT * FROM T_PESSOA WHERE IN_ATIVO = 'A' ORDER BY NR_ANTIGUIDADE asc ");
		Query query = this.em.createNativeQuery(sql.toString(), Pessoa.class);
	
		return query.getResultList();
	}
	
	public Pessoa obterPessoaByCPF(String cpf) {
		Pessoa pessoa = null;
		if (cpf != null) {
			try {
				StringBuilder sql = new StringBuilder(" SELECT * FROM T_PESSOA PES WHERE PES.NR_CPF = ? ");
				Query query = this.em.createNativeQuery(sql.toString(), Pessoa.class);
				int i = 0;
				query.setParameter(++i, cpf);
				pessoa = (Pessoa) query.getSingleResult();
			} catch (NoResultException e) {
				pessoa = null;
			}
		}
		return pessoa;
	}


// Busca Paginada usando SQL
	@SuppressWarnings("rawtypes")
	public List<Pessoa> getPessoaPaginatedSQL(int startPage, int maxPage, Map<String, Object> filters) {

		StringBuilder query = new StringBuilder();

		query.append(" SELECT PES.*");
		query.append(" FROM T_PESSOA PES");
		query.append(" WHERE 1=1 ");
		query.append(" AND PES.IN_ATIVO ='A' ");

		// CONVERTER OS FILTROS DA DATATABLE LAZY PARA OS PARAMETROS NORMAIS
		int i = 0;
		Map<String, Object> parameters = new HashMap<String, Object>();

		for (Iterator it = filters.keySet().iterator(); it.hasNext();) {
			String atributo = (String) it.next(); // Atributo da entidade
			String valorAtributo = (String) filters.get(atributo); // valor
																	// pesquisado
			String queryAnd = "";
			if (atributo.equals("nrSaram")) {
				queryAnd = " AND NR_SARAM = ? ";
			}
			if (atributo.equals("nmPessoa")) {
				queryAnd = " AND NM_PESSOA LIKE ? ";
			}
			if (atributo.equals("nmGuerra")) {
				queryAnd = " AND NM_GUERRA LIKE ? ";
			}

			query.append(queryAnd);
			parameters.put(((++i) + ""), valorAtributo);
		}

		query.append(" ORDER BY PES.NM_PESSOA ");

		return super.findResultsNativeQueryPaginated(query.toString(), parameters, startPage, maxPage);
	}

	// Count Paginado usando SQL
	@SuppressWarnings("rawtypes")
	public long countPessoaTotalSQL(Map<String, Object> filters) {

		StringBuilder query = new StringBuilder();

		query.append(" SELECT COUNT(1) ");
		query.append(" FROM T_PESSOA PES");
		query.append(" WHERE 1=1 ");
		query.append(" AND PES.IN_ATIVO ='A' ");

		// CONVERTER OS FILTROS DA DATATABLE LAZY PARA OS PARAMETROS NORMAIS
		int i = 0;
		Map<String, Object> parameters = new HashMap<String, Object>();
		// parameters.put(((++i)+""), identificadorSuprimento);
		for (Iterator it = filters.keySet().iterator(); it.hasNext();) {
			String atributo = (String) it.next(); // Atributo da entidade
			String valorAtributo = (String) filters.get(atributo); // valor
																	// pesquisado
			String queryAnd = "";
			if (atributo.equals("nrSaram")) {
				queryAnd = " AND NR_SARAM = ? ";
			}
			if (atributo.equals("nmPessoa")) {
				queryAnd = " AND NM_PESSOA LIKE ? ";
			}
			if (atributo.equals("nmGuerra")) {
				queryAnd = " AND NM_GUERRA LIKE ? ";
			}

			query.append(queryAnd);
			parameters.put(((++i) + ""), valorAtributo);
		}

		// ORDENACAO COMENTADA POR MOTIVO DE PERFORMANCE :: ANALISAR
		// if(i>1){
		// query.append(" ORDER BY M.NR_PN, M.CD_CFF");
		// }

		return super.findResultsNativeQueryPaginatedCount(query.toString(), filters);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> buscarByNome(String nomeBusca) {
		List<Pessoa> pessoa = null;
		StringBuilder sql = new StringBuilder(" SELECT * FROM T_PESSOA PES WHERE PES.NM_PESSOA LIKE '%' ? '%' ");
		Query query = this.em.createNativeQuery(sql.toString(), Pessoa.class);
		int i = 0;
		query.setParameter(++i, nomeBusca);
		pessoa = (List<Pessoa>) query.getResultList();
		return pessoa;
	}

}