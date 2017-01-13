package com.algaworks.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.projeto.model.Fabricante;
import com.algaworks.projeto.util.jpa.Transacional;

public class FabricanteDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {

		return em.createQuery("from Fabricante").getResultList();
	}

	
}
