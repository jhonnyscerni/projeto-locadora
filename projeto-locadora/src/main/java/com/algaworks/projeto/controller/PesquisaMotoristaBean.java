package com.algaworks.projeto.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.projeto.dao.MotoristaDAO;
import com.algaworks.projeto.model.Motorista;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMotoristaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	MotoristaDAO motoristaDAO;
	
	private List<Motorista> motoristas;
	
	private Motorista motoristaSelecionado;
	
	public List<Motorista> getMotoristas() {
		return motoristas;
	}
	
	public void excluir() {
		try {
			motoristaDAO.excluir(motoristaSelecionado);
			this.motoristas.remove(motoristaSelecionado);
			FacesUtil.addInfoMessage("Motorista " + motoristaSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Motorista getMotoristaSelecionado() {
		return motoristaSelecionado;
	}
	public void setMotoristaSelecionado(Motorista motoristaSelecionado) {
		this.motoristaSelecionado = motoristaSelecionado;
	}
	
	@PostConstruct
	public void inicializar() {
		motoristas = motoristaDAO.buscarTodos();
	}
}