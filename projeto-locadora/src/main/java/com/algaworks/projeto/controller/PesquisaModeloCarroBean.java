package com.algaworks.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.dao.ModeloCarroDAO;
import com.algaworks.projeto.model.ModeloCarro;

@Named
@ViewScoped
public class PesquisaModeloCarroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ModeloCarro> modelos;
	
	
	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	@PostConstruct
	public void init(){
		this.modelos = modeloCarroDAO.buscarModelo();
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}
	
	
	
	

}
