package com.algaworks.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.projeto.model.Motorista;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jpa.Transacional;

public class MotoristaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Motorista motorista){
		manager.merge(motorista);
	}
	
	public Motorista porId(Long codigo){
		return  manager.find(Motorista.class, codigo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Motorista> buscarTodos() {
		return manager.createQuery("from Motorista").getResultList();
	}
	
	@Transacional
	public void excluir(Motorista motorista) throws NegocioException{
		motorista = porId(motorista.getCodigo());
		try {
			manager.remove(motorista);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Motorista não pode ser Excluido");
		}
		
	}
	

}
