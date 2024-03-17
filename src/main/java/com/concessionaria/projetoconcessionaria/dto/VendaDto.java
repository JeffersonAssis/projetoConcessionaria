package com.concessionaria.projetoconcessionaria.dto;

import com.concessionaria.projetoconcessionaria.models.Cliente;
import com.concessionaria.projetoconcessionaria.models.Veiculo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VendaDto {


	
	private float comissao;

	private float valorVenda;
	

	private FuncionarioDto funcionario;

	private Veiculo  veiculo;
	
	private Cliente cliente;

	public float getComissao() {
		return comissao;
	}

	public void setComissao(float comissao) {
		this.comissao = comissao;
	}

	public float getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}

	public FuncionarioDto getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDto funcionario) {
		this.funcionario = funcionario;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
