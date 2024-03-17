package com.concessionaria.projetoconcessionaria.dto;


import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaDto {
	

	 @NotNull(message = "O nome precisa ser informado")
	 @NotBlank(message = "O nome não pode ser vazio")
	 @Size(min=3, max=255, message="O nome deve ter entre 3 e 255 caracteres")
	 @Pattern.List({
	            @Pattern(regexp = "^[\\p{Alpha} ]*$", message = "O nome deve conter apenas letras e espaço"),
	            @Pattern(regexp = "^\\S.*$", message = "O nome não pode iniciar com espaço"),
	            @Pattern(regexp = "^.*[\\S]$", message = "O nome não pode terminar com espaço"),
	            @Pattern(regexp = "^((?! {2}).)*$", message = "O nome não pode conter espaços consecutivos"),
	            @Pattern(regexp = "^[^a-z].*$", message = "O nome não pode iniciar com letra minúscula" ),
	    })
	private String nome;
	
	@NotNull(message = "O CPF precisa ser informado")
	@NotBlank(message = "O CPF não pode ser vazio")
	@CPF(message = "O CPF informado Invalido")
	private String cpf;



	private int idade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}


	
	
}
