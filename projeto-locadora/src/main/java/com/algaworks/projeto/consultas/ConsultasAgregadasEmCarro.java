package com.algaworks.projeto.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.algaworks.projeto.model.Carro;

public class ConsultasAgregadasEmCarro {

	public static void main(String[] args) {
		EntityManagerFactory entinty = Persistence.createEntityManagerFactory("AlgaWorksPU").createEntityManager()
				.getEntityManagerFactory();
		EntityManager em = entinty.createEntityManager();

		String jpql = "select c, count(a), max(a.valorTotal), avg(a.valorTotal) " + "from Carro c JOIN c.alugueis a "
				+ "group by c " + "having count(a) > 1";

		List<Object[]> resultados = em.createQuery(jpql).getResultList();
		for (Object[] obj : resultados) {
			System.out.println("Modelo: " + ((Carro) obj[0]).getModeloCarro().getDescricao());
			System.out.println("Quantidade de alugueis: " + obj[1]);
			System.out.println("Valor máximo: " + obj[2]);
			System.out.println("Valor médio: " + obj[3]);
			System.out.println();
		}

		em.close();

	}

}
