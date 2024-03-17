package com.concessionaria.projetoconcessionaria.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.concessionaria.projetoconcessionaria.dto.VeiculoDto;
import com.concessionaria.projetoconcessionaria.models.Veiculo;
import com.concessionaria.projetoconcessionaria.repository.VeiculoRepository;
import com.concessionaria.projetoconcessionaria.utils.BindingResultValidation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	 @Autowired
	 private VeiculoRepository veiculoRepository;

	    @CrossOrigin(origins = "*")
	    @PostMapping("")
	    public ResponseEntity<?> createVeiculo(@RequestBody @Valid VeiculoDto veiculoDto, BindingResult bindingResult) {
	        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
	        if(bindingResultValidation.hasErrors()) {
	            return ResponseEntity.badRequest().body(bindingResultValidation.getErrors());
	        }
	        try {
	            Veiculo v = new Veiculo();
	          	v.setPlaca(veiculoDto.getPlaca());
				v.setMarca(veiculoDto.getMarca());
				v.setModelo(veiculoDto.getModelo());
				v.setAno(veiculoDto.getAno());
				v.setValorVeiculo(veiculoDto.getValorVeiculo());
	            v = veiculoRepository.save(v);
	            return new ResponseEntity<>(v, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o Veiculo: " + e.getMessage());
	        }
	    }

	    @CrossOrigin(origins = "*")
	    @GetMapping("/{id}")
	    public ResponseEntity<?> getVeiculo(@PathVariable Long id) {
	        try {
	            Optional<Veiculo> busca = veiculoRepository.findById(id);
	            if (busca.isPresent()) {
	                Veiculo veiculo = busca.get();
	                return ResponseEntity.ok(veiculo);
	            } else {
	                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum veiculo foi encontrado");
	            }
	        } catch(Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar o Veiculo: " + e.getMessage());
	        }
	    }

	    @CrossOrigin(origins = "*")
	    @GetMapping("")
	    public ResponseEntity<?> listVeiculo(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int limit) {
	        try {
	         //   Pageable pagina = (Pageable) PageRequest.of(offset, limit);
	          //  Page<Veiculo> allVeiculo = veiculoRepository.findAll(pagina);
	          //  HttpHeaders responseHeaders = new HttpHeaders();
	          //  responseHeaders.set("X-Total-Count", Long.toString(veiculoRepository.count()));
	           // responseHeaders.set("X-Offset", Integer.toString(offset));
	           // responseHeaders.set("X-Limit", Integer.toString(limit));
	           
	        	List<Veiculo> vList = new ArrayList<>();
	        	vList = veiculoRepository.findAll();
	        	
	        	return ResponseEntity.ok().body(vList);
	        } catch(Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar Veiculos: " + e.getMessage());
	        }
	    }

	    @CrossOrigin(origins = "*")
	    @PutMapping("/{id}")
	    public ResponseEntity<?> updateVeiculo(@PathVariable Long id, @RequestBody @Valid VeiculoDto veiculoDto, BindingResult bindingResult){
	        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
	        if(bindingResultValidation.hasErrors()) {
	            return ResponseEntity.badRequest().body(bindingResultValidation.getErrors());
	        }
	        try {
	            Optional<Veiculo> busca = veiculoRepository.findById(id);
	            if (busca.isPresent()) {
	                Veiculo v = busca.get();
									v.setPlaca(veiculoDto.getPlaca());
									v.setMarca(veiculoDto.getMarca());
									v.setModelo(veiculoDto.getModelo());
									v.setAno(veiculoDto.getAno());
									v.setValorVeiculo(veiculoDto.getValorVeiculo());
	                veiculoRepository.save(v);
	                return ResponseEntity.ok(v);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum Veiculo encontrado com id: " + id);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o veiculo: " + e.getMessage());
	        }
	    }

	    @CrossOrigin(origins = "*")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deletaVeiculo(@PathVariable Long id){
	        boolean doesUsuarioExist = veiculoRepository.existsById(id);
	        if (doesUsuarioExist) {
	            veiculoRepository.deleteById(id);
	            return ResponseEntity.ok(id);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum veiculo encontrado com id: " + id);
	        }
	    }
	    @CrossOrigin(origins = "*")
	    @GetMapping("/placa/{placa}")
	    public ResponseEntity<?> getVeiculoPlaca(@PathVariable String placa) {
	        try {
	            Optional<Veiculo> busca = veiculoRepository.buscarPorPlaca(placa);
	            if (busca.isPresent()) {
	                Veiculo veiculo = busca.get();
	                return ResponseEntity.ok(veiculo);
	            } else {
	                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum veiculo foi encontrado");
	            }
	        } catch(Exception e) {
	        	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar o Veiculo: " + e.getMessage());
	        }
	    }
	    @CrossOrigin(origins = "*")
	    @DeleteMapping("/placa/{placa}")
	    public ResponseEntity<?> deletaVeiculoPlaca(@PathVariable String placa){
	        try {
	    	Optional<Veiculo> opVeiculo = veiculoRepository.buscarPorPlaca(placa);
	    	Veiculo v = opVeiculo.get();
	        if (v.getId() > 0) {
	            veiculoRepository.deleteById(v.getId());
	            return ResponseEntity.ok(v.getPlaca());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum veiculo encontrado com a placa: " + placa);
	        }
	        }catch (Exception e) {
	        	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar o Veiculo: " + e.getMessage());
			}
	    }
	    
	    @CrossOrigin(origins = "*")
	    @PutMapping("/placa/{placa}")
	    public ResponseEntity<?> updateVeiculoPlaca(@PathVariable String placa, @RequestBody @Valid VeiculoDto veiculoDto, BindingResult bindingResult){
	        BindingResultValidation bindingResultValidation = new BindingResultValidation(bindingResult);
	        if(bindingResultValidation.hasErrors()) {
	            return ResponseEntity.badRequest().body(bindingResultValidation.getErrors());
	        }
	        try {
	            Optional<Veiculo> busca = veiculoRepository.buscarPorPlaca(placa);
	            if (busca.isPresent()) {
	                Veiculo v = busca.get();
									v.setPlaca(veiculoDto.getPlaca());
									v.setMarca(veiculoDto.getMarca());
									v.setModelo(veiculoDto.getModelo());
									v.setAno(veiculoDto.getAno());
									v.setValorVeiculo(veiculoDto.getValorVeiculo());
	                veiculoRepository.save(v);
	                return ResponseEntity.ok(v);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum Veiculo encontrado com placa: " + placa);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o veiculo: " + e.getMessage());
	        }
	    }
}
