package com.algaworks.projeto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.algaworks.projeto.dao.AcessorioDAO;
import com.algaworks.projeto.dao.ModeloCarroDAO;
import com.algaworks.projeto.model.Acessorio;
import com.algaworks.projeto.model.Carro;
import com.algaworks.projeto.model.ModeloCarro;
import com.algaworks.projeto.service.CadastroCarroService;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroCarroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UploadedFile uploadedFile;
	
	private List<ModeloCarro> modelos;
	
	@Inject
	private CadastroCarroService cadastroCarroService;
	
	private Carro carro;
	
	private List<Acessorio> acessorios;
	
	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	@Inject
	private AcessorioDAO acessorioDAO;
	
	@PostConstruct
	public void init(){
		this.acessorios = acessorioDAO.buscarTodosAcessorios();
		this.modelos = modeloCarroDAO.buscarModelo();
		limpar();
	}

	private void limpar() {
		this.carro = new Carro();
		this.carro.setAcessorios(new ArrayList<Acessorio>());
		
	}
	
	public void salvar(){
		try {
			
			if (this.uploadedFile != null ) {
				this.carro.setFoto(this.uploadedFile.getContents());
			}
			
			cadastroCarroService.salvar(carro);
			FacesUtil.addInfoMessage("Carro cadastrado com Sucesso");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public List<ModeloCarro> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloCarro> modelos) {
		this.modelos = modelos;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	
	
	
	
	

	
}
