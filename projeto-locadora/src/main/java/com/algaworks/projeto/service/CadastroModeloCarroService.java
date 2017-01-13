package com.algaworks.projeto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.projeto.dao.ModeloCarroDAO;
import com.algaworks.projeto.model.ModeloCarro;
import com.algaworks.projeto.util.jpa.Transacional;

public class CadastroModeloCarroService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	@Transacional
	public void salvar(ModeloCarro modeloCarro) throws NegocioException
	{
		if (modeloCarro.getDescricao() == null || modeloCarro.getDescricao().trim().equals("")) {
			throw new NegocioException("O nome do modelo é obrigatório");
		}
		
		if (modeloCarro.getFabricante() == null) {
			throw new NegocioException("O fabricante é obrigatório");
		}
		
		this.modeloCarroDAO.salvar(modeloCarro);
	}
}
