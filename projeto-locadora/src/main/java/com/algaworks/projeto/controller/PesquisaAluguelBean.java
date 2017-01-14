package com.algaworks.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.dao.AluguelDAO;
import com.algaworks.projeto.dao.ModeloCarroDAO;
import com.algaworks.projeto.model.Aluguel;
import com.algaworks.projeto.model.Carro;
import com.algaworks.projeto.model.ModeloCarro;

@Named
@ViewScoped
public class PesquisaAluguelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Aluguel aluguel;
	
	private Carro carro;
	
	private List<Aluguel> alugueis;
	
	private List<ModeloCarro> modelosCarros;
	
	@Inject
	private ModeloCarroDAO modeloCarroDAO;
	
	@Inject
	private AluguelDAO aluguelDAO;
	
	@PostConstruct
	public void init(){
		
		this.aluguel = new Aluguel();
		this.carro = new Carro();
		this.setModelosCarros(modeloCarroDAO.buscarModelo());
		this.alugueis = aluguelDAO.buscarTodos();
	}
	
	public void pesquisar(){
		this.alugueis = aluguelDAO.buscarPorDataDeEntregaEModeloCarro(this.aluguel.getDataEntrega(), this.carro.getModeloCarro());
	}

	public List<Aluguel> getAlugueis() {
		return alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<ModeloCarro> getModelosCarros() {
		return modelosCarros;
	}

	public void setModelosCarros(List<ModeloCarro> modelosCarros) {
		this.modelosCarros = modelosCarros;
	}
	
		
}
