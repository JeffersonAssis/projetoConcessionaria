package com.concessionaria.projetoconcessionaria.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {

	@Id
	private String cep;
	@Column(columnDefinition = "VARCHAR(100)", nullable = false)
	private String logradouro;
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String bairro;
	@Column(columnDefinition = "VARCHAR(50)", nullable = false)
	private String localidade;
	@Column(columnDefinition = "VARCHAR(30)", nullable = false)
	private String uf;


	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep.replace("-","");
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}


}
