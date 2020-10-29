package br.mil.fab.controle.service;

import java.util.List;

import javax.ejb.Local;

import br.mil.fab.controle.entities.Inspecao;

@Local
public interface InspecaoService {
	
    public Inspecao getInspecaoByCdInspecao(Long cdInpsecao) ;
    public List<Inspecao> obterInspecoesPessoa(Long cdPessoa);
    public void salvarInspecao(Inspecao inspecao);
	public void editarInspecao(Inspecao inspecao);
	public void removerInspecao(Inspecao inspecaoRemocao);

}
