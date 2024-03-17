package com.concessionaria.projetoconcessionaria.dto;

import com.concessionaria.projetoconcessionaria.models.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ClienteDto extends PessoaDto {
	
	@NotNull(message = "O Telefone precisa ser informado")
    @NotBlank(message = "O Telefone não pode ser vazio")
    @Size(min=9, max=11, message="O Telefone deve ter entre 9 e 11 nuemros")
	private String telefone;

	@NotNull(message = "O numero da casa precisa ser informado")
	@NotBlank(message = "O numero da casa não pode ser vazio")
	private String numeroCasa;

    private Endereco enderecos;

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

