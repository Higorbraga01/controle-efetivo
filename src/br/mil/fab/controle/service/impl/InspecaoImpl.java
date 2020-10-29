package br.mil.fab.controle.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.mil.fab.controle.dao.InspecaoDAO;
import br.mil.fab.controle.entities.Inspecao;
import br.mil.fab.controle.service.InspecaoService;

@Stateless(name = "InspecaoImpl", mappedName = "portal-Model-InspecaoImpl")
public class InspecaoImpl implements InspecaoService{
	
	@EJB
	private transient InspecaoDAO inspecaoDao;

	@Override
	public Inspecao getInspecaoByCdInspecao(Long cdInpsecao) {
		return inspecaoDao.obterInspecaoById(cdInpsecao);
	}

	@Override
	public List<Inspecao> obterInspecoesPessoa(Long cdPessoa) {
		return inspecaoDao.buscarInspecoesPessoa(cdPessoa);
	}

	@Override
	public void salvarInspecao(Inspecao inspecao) {
		inspecaoDao.save(inspecao);
		
	}

	@Override
	public void editarInspecao(Inspecao inspecao) {
		inspecaoDao.update(inspecao);
		
	}

	@Override
	public void removerInspecao(Inspecao inspecaoRemocao) {
		inspecaoDao.deletar(inspecaoRemocao);;
		
	}

}
