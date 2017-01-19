package com.algaworks.projeto.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConsultaAcessorioPorCarro {

	public static void main(String[] args) {
		EntityManagerFactory entinty = Persistence.createEntityManagerFactory("AlgaWorksPU").createEntityManager()
				.getEntityManagerFactory();
		EntityManager em = entinty.createEntityManager();

		String jpql = "select a.descricao from Carro c JOIN c.acessorios a where c.modeloCarro.descricao = 'Gol'";
		List<String> acessorios = em.createQuery(jpql, String.class).getResultList();

		for (String modelo : acessorios) {
			System.out.println("<Acessorios> " + modelo);
		}

		em.close();
	}
}
