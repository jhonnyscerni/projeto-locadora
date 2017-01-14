package com.algaworks.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.dao.AcessorioDAO;
import com.algaworks.projeto.model.Acessorio;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaAcessorioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Acessorio acessorioSelecionado;
	
	@Inject
	private AcessorioDAO acessorioDAO;
	
	private List<Acessorio> acessorios;
	
	@PostConstruct
	public void init(){
		this.acessorioSelecionado = new Acessorio();
		this.acessorios = acessorioDAO.buscarTodosAcessorios();
	}
	
	public void excluir(){
		try {
			acessorioDAO.excluir(acessorioSelecionado);
			acessorios.remove(acessorioSelecionado);
			FacesUtil.addInfoMessage("Acessorio " + acessorioSelecionado.getDescricao() 
			+ " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Acessorio getAcessorioSelecionado() {
		return acessorioSelecionado;
	}

	public void setAcessorioSelecionado(Acessorio acessorioSelecionado) {
		this.acessorioSelecionado = acessorioSelecionado;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}
	
	
	
	
	
}
