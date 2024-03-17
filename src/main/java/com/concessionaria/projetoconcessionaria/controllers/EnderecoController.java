package com.concessionaria.projetoconcessionaria.controllers;

import com.concessionaria.projetoconcessionaria.dto.EnderecoDto;
import com.concessionaria.projetoconcessionaria.models.Endereco;
import com.concessionaria.projetoconcessionaria.repository.EnderecoRepository;
import com.concessionaria.projetoconcessionaria.service.ConsultaCep;
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

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private ConsultaCep consultaCep;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/{cep}")
    public ResponseEntity<?> getEndereco(@PathVariable String cep) {
        return new ResponseEntity<>(consultaCep.viaCep(cep), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public ResponseEntity<?> createEndereco(@RequestBody @Valid EnderecoDto enderecoDto, BindingResult bindingResult) {
        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
        if (bindingResultValidation.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResultValidation.getErrors());
        }
        try {
            String cep = enderecoDto.getCep();
            Optional<EnderecoDto> endE = consultaCep.viaCep(cep);
            Endereco end = new Endereco();

            if (endE.isPresent()) {
                EnderecoDto end1 = endE.get();
                System.out.println(end1.getCep());
                end.setCep(end1.getCep());
                end.setLogradouro(end1.getLogradouro());
                end.setBairro(end1.getBairro());
                end.setLocalidade(end1.getLocalidade());
                end.setUf(end1.getUf());


                end = enderecoRepository.save(end);
            }
                return new ResponseEntity<>(end, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Endereco: " + e.getMessage());
        }

    }
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ResponseEntity<?> listarEnderecos(){
        try {
            List<Endereco> endList = new ArrayList<>();
            endList = enderecoRepository.findAll();

            return ResponseEntity.ok().body(endList);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao listar todos os endereços!");
        }
    }

}
