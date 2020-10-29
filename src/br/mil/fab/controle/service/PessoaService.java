package br.mil.fab.controle.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.mil.fab.controle.entities.Pessoa;

@Local
public interface PessoaService {

    public List<Pessoa> obterPessoas();
    public List<Pessoa> obterPessoasInAtivo();
    public void salvarPessoa(Pessoa pessoa);
	public void editaPessoa(Pessoa pessoa);
    public List<Pessoa> getPessoaPaginatedSQL(int startPage, int maxPage, Map<String, Object> filters) ;	 
   	public long countPessoaTotalSQL(Map<String, Object> filters) ;
	public List<Pessoa> buscarByNome(String nomeBusca);
	public void removePessoaByCdPessoa(Pessoa pessoa);
	public Pessoa consultaPessoa(Long cdPessoa);

}
