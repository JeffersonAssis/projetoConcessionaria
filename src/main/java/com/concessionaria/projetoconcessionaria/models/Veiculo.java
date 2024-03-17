package com.concessionaria.projetoconcessionaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "veiculos")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "VARCHAR(7)", nullable = false)
	private String placa;
	
	@Column(columnDefinition = "VARCHAR(30)", nullable = false)
	private String marca;
	
	@Column(columnDefinition = "VARCHAR(20)", nullable = false)
	private String modelo;
	
	@Column( nullable = false)
	private int ano;
	
	@Column( name="valor_veiculo", nullable = false)
	private float valorVeiculo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
