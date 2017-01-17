package com.algaworks.projeto.criteria;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.query.criteria.internal.CriteriaSubqueryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.projeto.model.Aluguel;
import com.algaworks.projeto.model.Carro;
import com.algaworks.projeto.model.Carro_;
import com.algaworks.projeto.model.ModeloCarro;

public class ExemplosCriteria {

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
	
	//Somente as placas do veiculos e nao todo objeto
	//Por isso a Lista de String
	
	@Test
	public void projecoes() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.select(carro.<String>get("placa"));
		
		TypedQuery<String> query = manager.createQuery(criteriaQuery);
		List<String> placas = query.getResultList();
		
		for (String placa : placas) {
			System.out.println(placa);
		}
	}
	
	//Retorna o a somar de todos os algueis
	@Test
	public void funcoesDeAgregacao(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		//builder.max()
		//builder.sum()
		//builder.min()
		CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(BigDecimal.class);
		Root<Aluguel> aluguel = criteriaQuery.from(Aluguel.class);
		
		criteriaQuery.select(builder.sum(aluguel.<BigDecimal>get("valorTotal")));
		
		TypedQuery<BigDecimal> query = 	manager.createQuery(criteriaQuery);
		BigDecimal total = query.getSingleResult();
		
		System.out.println("somar de todos os aluguei: "+total);
	}
	
	//Não recomendado
	@Test
	public void resultadoComplexo(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		criteriaQuery.multiselect(carro.get("placa"),carro.get("valorDiaria"));
		
		TypedQuery<Object[]> query = manager.createQuery(criteriaQuery);
		List<Object[]> resultado = query.getResultList();
		
		for (Object[] objects : resultado) {
			System.out.println(objects[0]+ " - "+objects[1]);
		}
	}
	
	@Test
	public void resultadoTupla(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		
		criteriaQuery.multiselect(carro.get("placa").alias("placaCarro"), carro.get("valorDiaria").alias("valorCarro"));
		
		TypedQuery<Tuple> query = manager.createQuery(criteriaQuery);
		 
		List<Tuple> resultado = query.getResultList();
		
		for (Tuple tuple : resultado) {
			System.out.println(tuple.get("placaCarro")+" - "+tuple.get("valorCarro"));
		}
	}
	
	@Test
	public void resultadoConstrutores(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PrecoCarro> criteriaQuery = builder.createQuery(PrecoCarro.class);
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		
		criteriaQuery.select(builder.construct(PrecoCarro.class, carro.get("placa"), carro.get("valorDiaria")));
		
		TypedQuery<PrecoCarro> query = manager.createQuery(criteriaQuery);
		List<PrecoCarro> resultado = query.getResultList();
		
		for (PrecoCarro precoCarro : resultado) {
			System.out.println(precoCarro.getPlaca()+" - "+precoCarro.getValor());
			
		}
		
	}
	
	@Test
	public void funcaoOrdenacao(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Predicate predicate = builder.equal(builder.upper(carro.<String>get("cor")), "prata".toUpperCase());
		Order order = builder.desc(carro.get("valorDiaria"));
		
		criteriaQuery.select(carro);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(order);
		
		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();
		
		for (Carro c : carros) {
			System.out.println(c.getPlaca() + " - "+ c.getCor());
		}
	}
	
	@Test
	public void joinFetch(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Join<Carro, ModeloCarro> modeloCarro = (Join) carro.fetch("modeloCarro");
		
		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();
		
		for (Carro carro2 : carros) {
			System.out.println("carros: "+ carro2.getPlaca()+" - "+carro2.getModeloCarro().getDescricao());
		}
				
	}
	
	@Test
	public void mediaDiariasCarro(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
		
		Root<Carro> c = criteriaQuery.from(Carro.class);
		criteriaQuery.select(builder.avg(c.<Double>get("valorDiaria")));
		
		TypedQuery<Double> query = manager.createQuery(criteriaQuery);
		Double total = query.getSingleResult();
		
		System.out.println("valor total : "+total);
		
	}
	
	@Test
	public void carrosAcimaDaMedia(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		Subquery<Double> subquery = criteriaQuery.subquery(Double.class);
		
		Root<Carro> c = criteriaQuery.from(Carro.class);
		Root<Carro> csub = subquery.from(Carro.class);
		
		subquery.select(builder.avg(csub.<Double>get("valorDiaria")));		
		criteriaQuery.select(c);
		criteriaQuery.where(builder.greaterThanOrEqualTo(c.<Double>get("valorDiaria"), subquery));
		
		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> resultado = query.getResultList();
		
		for (Carro carro : resultado) {
			System.out.println("carro : "+carro.getPlaca()+" - "+ carro.getValorDiaria());
		}
		
	}
	
	@Test
	public void metaModel(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Carro> criteriaQuery = builder.createQuery(Carro.class);
		
		Root<Carro> carro = criteriaQuery.from(Carro.class);
		Join<Carro, ModeloCarro> modeloCarro = (Join) carro.fetch(Carro_.modeloCarro);
		
		TypedQuery<Carro> query = manager.createQuery(criteriaQuery);
		List<Carro> carros = query.getResultList();
		
		for (Carro carro2 : carros) {
			System.out.println("carros: "+ carro2.getPlaca()+" - "+carro2.getModeloCarro().getDescricao());
		}
	}
}
