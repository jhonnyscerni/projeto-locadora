package com.algaworks.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.dao.FabricanteDAO;
import com.algaworks.projeto.model.Categoria;
import com.algaworks.projeto.model.Fabricante;
import com.algaworks.projeto.model.ModeloCarro;
import com.algaworks.projeto.service.CadastroModeloCarroService;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroModeloCarroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ModeloCarro modeloCarro;
	
	@Inject
	private CadastroModeloCarroService cadastroModeloCarroService;
	
	private List<Fabricante> fabricantes;
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@PostConstruct
	public void init(){
		this.limpar();
		this.fabricantes = fabricanteDAO.buscarTodos();
	}
	
	public Categoria[] getCategorias(){
		return Categoria.values();
	}
	
	public void salvar(){
		try {
			this.cadastroModeloCarroService.salvar(modeloCarro);
			FacesUtil.addInfoMessage("Modelo Carro Cadastrado com Sucesso");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void limpar() {
		this.modeloCarro = new ModeloCarro();
		
	}

	public ModeloCarro getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(ModeloCarro modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	
	

}
