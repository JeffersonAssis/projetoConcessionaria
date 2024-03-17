package com.concessionaria.projetoconcessionaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="funcionarios")
public class Funcionario extends Pessoa{
	
	@Column(columnDefinition = "VARCHAR(12)", nullable = false)
	private String matricula;
	
	@Column(nullable = false)
	private double salario;

	public String getMatricula(String matricula) {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}
