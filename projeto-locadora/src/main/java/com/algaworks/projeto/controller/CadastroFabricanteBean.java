package com.algaworks.projeto.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.model.Fabricante;
import com.algaworks.projeto.service.CadastroFabricanteService;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;


@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	
	private Fabricante fabricante;
	
	@PostConstruct
	public void init(){
		this.limpar();
	}
	
	public void salvar(){
		try {
			this.cadastroFabricanteService.salvar(fabricante);
			FacesUtil.addInfoMessage("Fabricante cadastrado com Sucesso!");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	private void limpar() {
		this.fabricante = new Fabricante();
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	
	
	
}
