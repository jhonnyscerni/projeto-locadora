package com.algaworks.projeto.model;

public enum Categoria {

	HATCH_COMPACTO("HATCH_COMPACTO"),
	HATCH_MEDIO("HATCH_MEDIO"),
	SEDAN_COMPACTO("SEDAN_COMPACTO"),
	SEDAN_MEDIO("SEDAN_MEDIO"),
	SEDAN_GRANDE("SEDAN_GRANDE"),
	MINIVAN("MINIVAN"),
	ESPORTIVO("ESPORTIVO"),
	UTILITARIO_COMERCIAL("UTILITARIO_COMERCIAL");
	
	private String descricao;
	
	private Categoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
