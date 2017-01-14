package com.algaworks.projeto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.projeto.dao.CarroDAO;
import com.algaworks.projeto.model.Carro;
import com.algaworks.projeto.util.jpa.Transacional;

public class CadastroCarroService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroDAO carroDAO;
	
	@Transacional
	public void salvar(Carro carro) throws NegocioException{
		if (carro.getPlaca() == null || carro.getPlaca().trim().equals("")) {
			throw new NegocioException("Placa do Carro deve ser preenchido");
		}
		this.carroDAO.salvar(carro);
	}

}
