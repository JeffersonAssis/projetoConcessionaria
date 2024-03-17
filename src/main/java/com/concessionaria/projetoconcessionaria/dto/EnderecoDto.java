package com.concessionaria.projetoconcessionaria.dto;

import com.concessionaria.projetoconcessionaria.models.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EnderecoDto {



	private String logradouro;

	
	@NotNull(message = "O CEP precisa ser informado")
	@NotBlank(message = "O CEP não pode ser vazio")
	private String cep;
	

	private String bairro;
	

	private String localidade;


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
		this.cep = cep;
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
