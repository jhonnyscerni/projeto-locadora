package com.algaworks.projeto.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.model.Funcionario;
import com.algaworks.projeto.model.Sexo;
import com.algaworks.projeto.service.CadastroFuncionarioService;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	@Inject
	private CadastroFuncionarioService cadastroFuncionarioService;

	@PostConstruct
	public void init() {
		limpar();
	}

	private void limpar() {
		this.funcionario = new Funcionario();
	}
	
	public void salvar(){
		try {
			this.cadastroFuncionarioService.salvar(funcionario);
			FacesUtil.addInfoMessage("Funcionario salvo com sucesso!");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Sexo[] getSexos(){
		return Sexo.values();
	}

}
