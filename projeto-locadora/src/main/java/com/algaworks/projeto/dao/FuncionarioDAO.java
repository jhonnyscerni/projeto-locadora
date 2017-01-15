package com.algaworks.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.projeto.model.Funcionario;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jpa.Transacional;
import com.algaworks.projeto.util.jsf.FacesUtil;

public class FuncionarioDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Funcionario funcionario){
		manager.merge(funcionario);
	}
	
	public Funcionario porId(Long codigo){
		return manager.find(Funcionario.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarTodos(){
		return manager.createQuery("from Funcionario").getResultList();
	}
	
	@Transacional
	public void excluir(Funcionario funcionario) throws NegocioException{
		funcionario = porId(funcionario.getCodigo());
		try {
			manager.remove(funcionario);
			manager.flush();
			FacesUtil.addInfoMessage("Funcionario "+funcionario.getNome()+" excluido com sucesso");
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionario não pode ser excluido");
		}
	}

}
