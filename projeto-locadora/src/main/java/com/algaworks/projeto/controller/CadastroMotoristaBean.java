package com.algaworks.projeto.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.model.Motorista;
import com.algaworks.projeto.model.Sexo;
import com.algaworks.projeto.service.CadastroMotoristaService;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMotoristaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Motorista motorista;
	
	@Inject
	private CadastroMotoristaService cadastroMotoristaService;
	
	@PostConstruct
	public void init(){
		limpar();
	}
	
	public void limpar(){
		this.motorista = new Motorista();
	}
	
	public void salvar(){
		try {
			this.cadastroMotoristaService.salvar(getMotorista());
			FacesUtil.addInfoMessage("Motoristo salvo com Sucesso");
			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public Sexo[] getSexos(){
		return Sexo.values();
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
}
