package com.algaworks.projeto.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.dao.CarroDAO;
import com.algaworks.projeto.dao.MotoristaDAO;
import com.algaworks.projeto.model.Aluguel;
import com.algaworks.projeto.model.ApoliceSeguro;
import com.algaworks.projeto.model.Carro;
import com.algaworks.projeto.model.Motorista;
import com.algaworks.projeto.service.CadastroAluguelService;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAluguelBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Aluguel aluguel;
	
	@Inject
	private CadastroAluguelService cadastroAluguelServie;
	
	private List<Carro> carros;
	
	@Inject
	private CarroDAO carroDAO;
	
	@Inject
	private MotoristaDAO motoristaDAO;
	
	private List<Motorista> motoristas;
	
	
	@PostConstruct
	public void init(){
			this.motoristas = this.motoristaDAO.buscarTodos();
			this.carros = this.carroDAO.buscarTodos();
			limpar();
	}
	
	public void salvar(){
		try {
			cadastroAluguelServie.salvar(aluguel);
			FacesUtil.addInfoMessage("Aluguel Cadastrado com sucesso");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void limpar() {
		this.aluguel = new Aluguel();
		this.aluguel.setApoliceSeguro(new ApoliceSeguro());
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public List<Motorista> getMotoristas() {
		return motoristas;
	}

	public void setMotoristas(List<Motorista> motoristas) {
		this.motoristas = motoristas;
	}

	
	
	
}
