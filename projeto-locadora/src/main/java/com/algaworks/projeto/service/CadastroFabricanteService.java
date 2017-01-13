package com.algaworks.projeto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.projeto.dao.FabricanteDAO;
import com.algaworks.projeto.model.Fabricante;
import com.algaworks.projeto.util.jpa.Transacional;


public class CadastroFabricanteService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@Transacional
	public void salvar(Fabricante fabricante) throws NegocioException {
		if (fabricante.getNome() == null) {
			throw new NegocioException("Vazio");
		}
		this.fabricanteDAO.salvar(fabricante);
	}

}
