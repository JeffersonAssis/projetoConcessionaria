package com.concessionaria.projetoconcessionaria.controllers;

import com.concessionaria.projetoconcessionaria.dto.ClienteDto;
import com.concessionaria.projetoconcessionaria.dto.EnderecoDto;
import com.concessionaria.projetoconcessionaria.models.Cliente;
import com.concessionaria.projetoconcessionaria.models.Endereco;
import com.concessionaria.projetoconcessionaria.repository.ClienteRepository;
import com.concessionaria.projetoconcessionaria.repository.EnderecoRepository;
import com.concessionaria.projetoconcessionaria.service.ConsultaCep;
import com.concessionaria.projetoconcessionaria.utils.BindingResultValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.Origin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ConsultaCep consultaCep;

    @CrossOrigin(origins = "*")
    @PostMapping("")
    public ResponseEntity<?> createClietne(@RequestBody @Valid ClienteDto clienteDto, BindingResult bindingResult){
        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
        if(bindingResultValidation.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResultValidation.getErrors());
        }
        try {
            Cliente cl = new Cliente();
            Endereco end = new Endereco();
            String cep = clienteDto.getEnderecos().getCep();
           Optional<Endereco> e =  enderecoRepository.findById(cep);

            if(e.isPresent()){
                System.err.println("oiiii");
                end = e.get();
                cl.setNome(clienteDto.getNome());
                cl.setCpf(clienteDto.getCpf());
                cl.setTelefone(clienteDto.getTelefone());
                cl.setIdade(clienteDto.getIdade());
                cl.setEnderecos(end);
                cl.setNumeroCasa(clienteDto.getNumeroCasa());

                cl = clienteRepository.save(cl);
            }else{
               Optional<EnderecoDto> ende = consultaCep.viaCep(cep);
               if (ende.isPresent()) {
                   EnderecoDto end1 = ende.get();
                   end.setCep(end1.getCep());
                   end.setLogradouro(end1.getLogradouro());
                   end.setBairro(end1.getBairro());
                   end.setLocalidade(end1.getLocalidade());
                   end.setUf(end1.getUf());


                   end = enderecoRepository.save(end);
               }else{
                   return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CEP informado invalido!");
               }
                   cl.setNome(clienteDto.getNome());
                   cl.setCpf(clienteDto.getCpf());
                   cl.setTelefone(clienteDto.getTelefone());
                   cl.setIdade(clienteDto.getIdade());
                   cl.setNumeroCasa(clienteDto.getNumeroCasa());

                cl.setEnderecos(end);

                  cl = clienteRepository.save(cl);
               }
               return new ResponseEntity<>(cl, HttpStatus.OK);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar Cliente: " + e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarClienteCpf(@PathVariable String cpf){
        try{
            Optional<Cliente> cli = clienteRepository.getCliente(cpf);
           if(cli.isPresent()){
             Cliente  cl = cli.get();

               return ResponseEntity.ok(cl);
           }else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado: "+ cpf);
           }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar o cliente: " + e.getMessage());

        }

    }

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ResponseEntity<?> getAllCLientes(){
        try {
            List<Cliente> clList = new ArrayList<>();
                    clList =  clienteRepository.findAll();
            return ResponseEntity.ok().body(clList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar os CLientes: " + e.getMessage());

        }

    }

    @PutMapping("/{cpf}")
    public ResponseEntity<?> updateCliente(@PathVariable String cpf, @RequestBody @Valid ClienteDto clienteDto, BindingResult bindingResult){
        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
        if(bindingResultValidation.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResultValidation.getErrors());
        }
        try {
            Optional<Cliente> buscar = clienteRepository.getCliente(cpf);
            if(buscar.isPresent()){
              Cliente cl = buscar.get();
                cl.setNome(clienteDto.getNome());
                cl.setCpf(clienteDto.getCpf());
                cl.setTelefone(clienteDto.getTelefone());
                cl.setIdade(clienteDto.getIdade());
                cl.setEnderecos(clienteDto.getEnderecos());

                cl = clienteRepository.save(cl);

                return ResponseEntity.ok(cl);
            }else{
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cliente cadastrado com o CPF: "+cpf);
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nenhum cliente encontrado " +e.getMessage());
        }

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> deleterPorCpf(@PathVariable String cpf){
        try {
            Optional<Cliente> cl = clienteRepository.getCliente(cpf);
            if(cl.isPresent()){
                Cliente c = cl.get();
                clienteRepository.deleteById(c.getId());
                return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido com sucesso CPF: "+ cpf);

            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado CPF: " + cpf);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar excluir o cliente");
        }

    }
}
