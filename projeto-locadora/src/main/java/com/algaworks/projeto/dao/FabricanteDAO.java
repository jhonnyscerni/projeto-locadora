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

	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}

	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos() {

		return em.createQuery("from Fabricante").getResultList();
	}

	@Transacional
	public void excluir(Fabricante fabricanteSelecionado){
			fabricanteSelecionado = porId(fabricanteSelecionado.getCodigo());
			em.remove(fabricanteSelecionado);
			em.flush();
	}
	
	public Fabricante porId(Long codigo){
		return em.find(Fabricante.class, codigo);
	}
}
