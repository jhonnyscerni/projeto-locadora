package com.algaworks.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.projeto.model.ModeloCarro;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jpa.Transacional;

public class ModeloCarroDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public ModeloCarro porId(Long codigo) {
		return manager.find(ModeloCarro.class, codigo);
	}

	public void salvar(ModeloCarro modeloCarro) {
		manager.merge(modeloCarro);
	}

	public List<ModeloCarro> buscarModelo() {
		return manager.createQuery("from ModeloCarro", ModeloCarro.class).getResultList();
	}

	@Transacional
	public void excluir(ModeloCarro modeloCarro) throws NegocioException {
		modeloCarro = porId(modeloCarro.getCodigo());

		try {
			manager.remove(modeloCarro);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Este modelo não pode ser excluido");
		}
	}
}
