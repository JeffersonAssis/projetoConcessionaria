package com.concessionaria.projetoconcessionaria.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name= "clientes")
public class Cliente extends Pessoa {
	
	@Column(columnDefinition = "VARCHAR(12)", nullable = false)
	private String telefone;

	@ManyToOne
	private Endereco enderecos;

	@Column(columnDefinition = "VARCHAR(10)", nullable = false)
	private String numeroCasa;


	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Endereco enderecos) {
		this.enderecos = enderecos;
	}


	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

}
