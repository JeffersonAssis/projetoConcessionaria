package com.concessionaria.projetoconcessionaria.controllers;

import com.concessionaria.projetoconcessionaria.dto.VendaDto;
import com.concessionaria.projetoconcessionaria.models.Cliente;
import com.concessionaria.projetoconcessionaria.models.Funcionario;
import com.concessionaria.projetoconcessionaria.models.Veiculo;
import com.concessionaria.projetoconcessionaria.models.Venda;
import com.concessionaria.projetoconcessionaria.repository.ClienteRepository;
import com.concessionaria.projetoconcessionaria.repository.FuncionarioRepository;
import com.concessionaria.projetoconcessionaria.repository.VeiculoRepository;
import com.concessionaria.projetoconcessionaria.repository.VendaRepository;
import com.concessionaria.projetoconcessionaria.utils.BindingResultValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/vendas")
@RestController
public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    VeiculoRepository veiculoRepository;


    @PostMapping("")
    public ResponseEntity<?> createVenda(@RequestBody @Valid VendaDto vendaDto, BindingResult bindingResult){
        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
        if(bindingResultValidation.hasErrors()){
            return ResponseEntity.status(HttpStatus.OK).body(bindingResultValidation.getErrors());
        }
        Venda v = new Venda();
        String cpf = vendaDto.getCliente().getCpf();
        String matricula = vendaDto.getFuncionario().getMatricula();
        String placa = vendaDto.getVeiculo().getPlaca();

        try {
            Optional<Cliente> opCL = clienteRepository.getCliente(cpf);
            Optional<Veiculo> opVeiculo = veiculoRepository.buscarPorPlaca(placa);
            Optional<Funcionario> opFun = funcionarioRepository.getFunMatricula(matricula);

            if(opCL.isPresent() && opVeiculo.isPresent() && opFun.isPresent()){
                Veiculo vl = opVeiculo.get();
                Cliente cl = opCL.get();
                Funcionario fl = opFun.get();

                v.setCliente(cl);
                v.setFuncionario(fl);
                v.setVeiculo(vl);
                float valorvenda = vl.getValorVeiculo();
                v.setValorVenda(valorvenda+(valorvenda * 0.05F));
                v.setComissao(valorvenda*0.005F);
                v = vendaRepository.save(v);

            }

            return ResponseEntity.ok(v);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao cadastrar venda : "+cpf);
        }
    }
}
