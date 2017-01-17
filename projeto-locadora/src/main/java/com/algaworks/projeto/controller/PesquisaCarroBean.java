package com.algaworks.projeto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.dao.CarroDAO;
import com.algaworks.projeto.model.Carro;
import com.algaworks.projeto.model.lazy.LazyCarroDataModel;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CarroDAO carroDAO;
	
	private List<Carro> carros = new ArrayList<>();
	
	private LazyCarroDataModel lazyCarroDataModel;
	
	private Carro carroSelecionado;
	private Carro carroSelecionadoParaExcluir;
	
	@PostConstruct
	public void init() {
		lazyCarroDataModel = new LazyCarroDataModel(carroDAO);
		//carros = carroDAO.buscarTodos();
	}
	
	public List<Carro> getCarros() {
		return carros;
	}
	
	public void excluir() {
		try {
			carroDAO.excluir(getCarroSelecionadoParaExcluir());
			this.carros.remove(getCarroSelecionadoParaExcluir());
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}
	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}
		
	
	public void buscarCarroComAcessorios() {
		carroSelecionado = carroDAO.buscarCarroComAcessorios(carroSelecionado.getCodigo());
	}

	public LazyCarroDataModel getLazyCarroDataModel() {
		return lazyCarroDataModel;
	}

	public void setLazyCarroDataModel(LazyCarroDataModel lazyCarroDataModel) {
		this.lazyCarroDataModel = lazyCarroDataModel;
	}

	public Carro getCarroSelecionadoParaExcluir() {
		return carroSelecionadoParaExcluir;
	}

	public void setCarroSelecionadoParaExcluir(Carro carroSelecionadoParaExcluir) {
		this.carroSelecionadoParaExcluir = carroSelecionadoParaExcluir;
	}
}