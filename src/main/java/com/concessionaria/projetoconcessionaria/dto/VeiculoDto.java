package com.concessionaria.projetoconcessionaria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VeiculoDto {
	
	@NotNull(message = "A palca precisa ser informado")
	@NotBlank(message = "A placa casa não pode ser vazio")
	private String placa;
	
	@NotNull(message = "A marca precisa ser informado")
	@NotBlank(message = "A marca casa não pode ser vazio")
	private String marca;
	
	@NotNull(message = "O modelo precisa ser informado")
	@NotBlank(message = "O modelo placa casa não pode ser vazio")
	private String modelo;

	@NotNull(message = "O ano precisa ser informado")
	private int ano;
	
	@NotNull(message = "O valor do veiculo precisa ser informado")
	private float valorVeiculo;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public float getValorVeiculo() {
		return valorVeiculo;
	}

	public void setValorVeiculo(float valorVeiculo) {
		this.valorVeiculo = valorVeiculo;
	}
	 
	
}
