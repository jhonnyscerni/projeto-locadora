package com.algaworks.projeto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.projeto.dao.MotoristaDAO;
import com.algaworks.projeto.model.Motorista;
import com.algaworks.projeto.util.jpa.Transacional;

public class CadastroMotoristaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MotoristaDAO motoristaDAO;
	
	@Transacional
	public void salvar(Motorista motorista) throws NegocioException{
		if (motorista.getCpf() == null) {
			throw new NegocioException("Cpf Obrigatorio");
		}
		this.motoristaDAO.salvar(motorista);
	}

}
