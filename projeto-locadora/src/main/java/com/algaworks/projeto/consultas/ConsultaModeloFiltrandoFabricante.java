package com.algaworks.projeto.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConsultaModeloFiltrandoFabricante {

	
	
	public static void main(String[] args) {
	    EntityManagerFactory entinty = Persistence.createEntityManagerFactory("AlgaWorksPU")
	    		.createEntityManager().getEntityManagerFactory();
		EntityManager em = entinty.createEntityManager();
		
		List<String> modeloFabrincante = em.createQuery("Select mc.descricao from ModeloCarro mc where mc.fabricante.nome = 'Honda'", String.class)
				.getResultList();
		
		for (String modelo : modeloFabrincante) {
			System.out.println("Modelos de Carro> "+modelo);
		}
		
		em.close();

	}

}
