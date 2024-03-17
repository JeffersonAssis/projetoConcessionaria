package com.concessionaria.projetoconcessionaria.controllers;

import com.concessionaria.projetoconcessionaria.dto.FuncionarioDto;
import com.concessionaria.projetoconcessionaria.models.Funcionario;
import com.concessionaria.projetoconcessionaria.repository.FuncionarioRepository;
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
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public ResponseEntity<?> createFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto, BindingResult bindingResult){
        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
        if(bindingResultValidation.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResultValidation.getErrors());
        }
        try {
            Funcionario fun = new Funcionario();

                fun.setNome(funcionarioDto.getNome());
                fun.setCpf(funcionarioDto.getCpf());
                fun.setMatricula(funcionarioDto.getMatricula());
                fun.setIdade(funcionarioDto.getIdade());
                fun.setSalario(funcionarioDto.getSalario());
                fun = funcionarioRepository.save(fun);

            return new ResponseEntity<>(fun, HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Funcionario: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{matricula}")
    public ResponseEntity<?> buscarFuncionarioMatricula(@PathVariable String matricula){
        try{
            Optional<Funcionario> fun = funcionarioRepository.getFunMatricula(matricula);
            if(fun.isPresent()){
                Funcionario  f = fun.get();

                return ResponseEntity.ok(f);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Funcionario não encontrado: "+ matricula);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar a matricula: " + e.getMessage());

        }

    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ResponseEntity<?> getAllFuncionarios(){
        try {
            List<Funcionario> funList = new ArrayList<>();
            funList =  funcionarioRepository.findAll();
            return ResponseEntity.ok().body(funList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar os Funcionarios: " + e.getMessage());

        }

    }

    @PutMapping("/{matricula}")
    public ResponseEntity<?> updateFuncionario(@PathVariable String matricula, @RequestBody @Valid FuncionarioDto funcionarioDto, BindingResult bindingResult){
        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
        if(bindingResultValidation.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResultValidation.getErrors());
        }
        try {
            Optional<Funcionario> buscar = funcionarioRepository.getFunMatricula(matricula);
            if(buscar.isPresent()){
                Funcionario fun = buscar.get();
                fun.setNome(funcionarioDto.getNome());
                fun.setCpf(funcionarioDto.getCpf());
                fun.setMatricula(funcionarioDto.getMatricula());
                fun.setIdade(funcionarioDto.getIdade());
                fun.setSalario(funcionarioDto.getSalario());

                fun = funcionarioRepository.save(fun);

                return ResponseEntity.ok(fun);
            }else{
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum Funcionario cadastrado com a matricula: "+matricula);
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nenhum funcionario encontrado " +e.getMessage());
        }

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{matricula}")
    public ResponseEntity<?> deleterPorMatricula(@PathVariable String matricula){
        try {
            Optional<Funcionario> fun = funcionarioRepository.getFunMatricula(matricula);
            if(fun.isPresent()){
                Funcionario f = fun.get();
                funcionarioRepository.deleteById(f.getId());
                return ResponseEntity.status(HttpStatus.OK).body("Funcionario excluido com sucesso Matricula: "+ matricula);

            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado Matricula: " + matricula);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar excluir o funcionario");
        }

    }

}
