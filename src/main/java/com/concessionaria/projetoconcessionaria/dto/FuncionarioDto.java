package com.concessionaria.projetoconcessionaria.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FuncionarioDto extends PessoaDto{
	
	@NotNull(message = "A Matricula precisa ser informado")
	private String matricula;
	
	@NotNull(message = "O Salario precisa ser informado")
	private double salario;

	public String getMatricula() {
		return matricula;
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
