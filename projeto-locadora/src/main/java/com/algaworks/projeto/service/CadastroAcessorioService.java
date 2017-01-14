package com.algaworks.projeto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.projeto.dao.AcessorioDAO;
import com.algaworks.projeto.model.Acessorio;
import com.algaworks.projeto.util.jpa.Transacional;

public class CadastroAcessorioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private AcessorioDAO acessorioDao;
	
	@Transacional
	public void salvar(Acessorio acessorio) throws NegocioException{
		if (acessorio.getDescricao() == null || acessorio.getDescricao().trim().equals("")) {
			throw new NegocioException("decrição não pode está vazia");
		}
		this.acessorioDao.salvar(acessorio);
	}
}
