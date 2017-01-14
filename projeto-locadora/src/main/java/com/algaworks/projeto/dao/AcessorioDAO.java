package com.algaworks.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.projeto.model.Acessorio;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jpa.Transacional;

public class AcessorioDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Acessorio porId(Long codigo){
		return manager.find(Acessorio.class, codigo);
	}
	
	@Transacional
	public void excluir(Acessorio acessorioSelecionado) throws NegocioException {
		acessorioSelecionado = porId(acessorioSelecionado.getCodigo());
		try {
			manager.remove(acessorioSelecionado);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Acessorio> buscarTodosAcessorios(){
		return manager.createQuery("from Acessorio").getResultList();
	}

	public void salvar(Acessorio acessorio) {
		manager.merge(acessorio);	
	}
}
