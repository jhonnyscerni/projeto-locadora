package com.algaworks.projeto.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.projeto.dao.FuncionarioDAO;
import com.algaworks.projeto.model.Funcionario;
import com.algaworks.projeto.util.jpa.Transacional;

public class CadastroFuncionarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Transacional
	public void salvar(Funcionario funcionario) throws NegocioException{
		this.funcionarioDAO.salvar(funcionario);
	}
}
