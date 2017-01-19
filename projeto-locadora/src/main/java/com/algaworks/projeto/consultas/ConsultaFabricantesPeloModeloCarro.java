package com.algaworks.projeto.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConsultaFabricantesPeloModeloCarro {

	
	
	public static void main(String[] args) {
	    EntityManagerFactory entinty = Persistence.createEntityManagerFactory("AlgaWorksPU")
	    		.createEntityManager().getEntityManagerFactory();
		EntityManager em = entinty.createEntityManager();
		
		List<String> nomeDosFabricantes = em.createQuery("select mc.fabricante.nome from ModeloCarro mc ", String.class).getResultList();
		
		for (String nomeFabricante : nomeDosFabricantes) {
			System.out.println("Nome do Fabrincante: " + nomeFabricante);
		}
		
		em.close();

	}

}
