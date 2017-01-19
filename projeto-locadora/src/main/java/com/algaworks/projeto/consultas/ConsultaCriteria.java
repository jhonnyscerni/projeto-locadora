package com.algaworks.projeto.consultas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.algaworks.projeto.model.Motorista;

public class ConsultaCriteria {

	public static void main(String[] args) {
		EntityManagerFactory entinty = Persistence.createEntityManagerFactory("AlgaWorksPU").createEntityManager()
				.getEntityManagerFactory();
		EntityManager em = entinty.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Motorista> criteriaQuery = builder.createQuery(Motorista.class);
		Root<Motorista> c = criteriaQuery.from(Motorista.class);
		criteriaQuery.select(c);
		criteriaQuery.where(builder.like(c.<String>get("nome"), "Jhonny%"));

		List<Motorista> motoristas = em.createQuery(criteriaQuery).getResultList();
		
		for (Motorista motorista : motoristas) {
			System.out.println("motorista->"+ motorista.getNome());
		}
		
		em.close();
	}
}
