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

	

	public List<ModeloCarro> buscarModelo() {
		return manager.createQuery("from ModeloCarro", ModeloCarro.class).getResultList();
	}

	
}
