package com.algaworks.projeto.model;

public enum Sexo {
	MASCULINO("Masculino"), 
	FEMININO("Feminino");
	
	private String descricao;
	
	

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
