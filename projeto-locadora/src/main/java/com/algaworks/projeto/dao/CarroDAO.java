package com.algaworks.projeto.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.projeto.model.Carro;
import com.algaworks.projeto.service.NegocioException;
import com.algaworks.projeto.util.jpa.Transacional;
import com.algaworks.projeto.util.jsf.FacesUtil;

public class CarroDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	@SuppressWarnings("unchecked")
	public List<Carro> buscarTodos(){
		return manager.createQuery("from Carro").getResultList();
	}
	
	public Carro porId(Long codigo){
		return manager.find(Carro.class, codigo);
	}
	
	public void salvar(Carro carro){
		manager.merge(carro);
	}
	
	@Transacional
	public void excluir(Carro carro) throws NegocioException{
		carro = porId(carro.getCodigo());
		try {
			manager.remove(carro);
			manager.flush();
			FacesUtil.addInfoMessage("Carro : " +carro.getPlaca()+ "excluido com sucesso");
		} catch (PersistenceException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Carro buscarCarroComAcessorios(Long codigo) {
		return manager.createNamedQuery("Carro.buscarCarroComAcessorios", Carro.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Carro> buscarComPaginacao(int first, int pageSize) {
		return manager.createQuery("from Carro",Carro.class)
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
	}

	public Long encontrarQuantidadeDeCarros() {
		return manager.createQuery("select count(c) from Carro c",Long.class).getSingleResult();
	}

}
