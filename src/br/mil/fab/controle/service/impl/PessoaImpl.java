package br.mil.fab.controle.service.impl;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.mil.fab.controle.dao.PessoaDAO;
import br.mil.fab.controle.entities.Pessoa;
import br.mil.fab.controle.service.PessoaService;

@Stateless(name = "PessoaImpl", mappedName = "portal-Model-PessoaImpl")
public class PessoaImpl implements PessoaService{
	
	@EJB
	private transient PessoaDAO pessoaDao;

	@Override
	public List<Pessoa> getPessoaPaginatedSQL(int startPage, int maxPage, Map<String, Object> filters) {
		return pessoaDao.getPessoaPaginatedSQL(startPage, maxPage, filters);
	}

	@Override
	public long countPessoaTotalSQL(Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void salvarPessoa(Pessoa pessoa) {
		pessoaDao.save(pessoa);
		
	}

	@Override
	public void editaPessoa(Pessoa pessoa) {
		pessoaDao.update(pessoa);		
	}

	@Override
	public List<Pessoa> obterPessoas() {
		return pessoaDao.findAll();
	}

	@Override
	public List<Pessoa> buscarByNome(String nomeBusca) {
		
		return pessoaDao.buscarByNome(nomeBusca);
	}

	@Override
	public void removePessoaByCdPessoa(Pessoa pessoa) {
		pessoaDao.update(pessoa);
		
	}

	@Override
	public List<Pessoa> obterPessoasInAtivo() {
		return pessoaDao.obterPessoasInAtivo();
	}

	@Override
	public Pessoa consultaPessoa(Long cdPessoa) {
		return pessoaDao.find(cdPessoa);
	}
}
