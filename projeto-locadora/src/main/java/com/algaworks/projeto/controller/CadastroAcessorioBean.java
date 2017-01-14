package com.algaworks.projeto.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.model.Acessorio;
import com.algaworks.projeto.service.CadastroAcessorioService;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAcessorioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Acessorio acessorio;
	
	@Inject
	private CadastroAcessorioService cadastroAcessorioService;
	
	
	@PostConstruct
	public void init(){
		limpar();
	}

	private void limpar() {
		this.acessorio = new Acessorio();
	}
	
	public void salvar(){
		try {
			cadastroAcessorioService.salvar(this.acessorio);
			FacesUtil.addInfoMessage("Acessorio Cadastrado com Sucesso");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}
	
	
}
