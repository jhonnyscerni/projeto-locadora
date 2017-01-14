package com.algaworks.projeto.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.inject.Inject;

import com.algaworks.projeto.dao.AluguelDAO;
import com.algaworks.projeto.model.Aluguel;

public class CadastroAluguelService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private AluguelDAO aluguelDAO;
	
	public void salvar(Aluguel aluguel) throws NegocioException{
		if (aluguel.getCarro() == null) {
			throw new NegocioException("O carro é Obrigatorio");
		}
		aluguel.setDataPedido(Calendar.getInstance());
		this.aluguelDAO.salvar(aluguel);
	}

}
