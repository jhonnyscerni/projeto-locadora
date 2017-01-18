package com.algaworks.projeto.criteria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.projeto.model.Carro;

public class ProblemaNMaisUm {

private static EntityManagerFactory factory;
	
	private EntityManager manager;
	
	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("AlgaWorksPU");
	}
	
	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		this.manager.close();
	}
	
	@Test
	public void problema() {
		TypedQuery<Carro> query = this.manager.createQuery("from Carro c inner join fetch c.modelo", Carro.class);
		List<Carro> carros = query.getResultList();
		
		for (Carro carro : carros) {
			System.out.println(carro.getPlaca() + " - " + carro.getModeloCarro().getDescricao());
		}
	}
	
}
