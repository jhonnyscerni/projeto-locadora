package com.algaworks.projeto.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultaModeloFiltroEmFabricanteECategoria {

	public static void main(String[] args) {
		EntityManagerFactory entinty = Persistence.createEntityManagerFactory("AlgaWorksPU").createEntityManager()
				.getEntityManagerFactory();
		EntityManager em = entinty.createEntityManager();

		String jpql = "select mc.descricao from ModeloCarro mc " + "where mc.fabricante.nome = 'Honda' "
				+ "and mc.categoria in ('SEDAN_MEDIO','SEDAN_GRANDE')";
		List<String> modelos = em.createQuery(jpql, String.class).getResultList();

		for (String modelo : modelos) {
			System.out.println("Modelos de Carro> " + modelo);
		}

		em.close();
	}
}
