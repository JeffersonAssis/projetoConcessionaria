package com.concessionaria.projetoconcessionaria.service;

import com.concessionaria.projetoconcessionaria.dto.EnderecoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="viacep", url = "https://viacep.com.br/ws")
public interface ConsultaCep {

    @GetMapping("/{cep}/json")
    Optional<EnderecoDto> viaCep(@PathVariable String cep);
}
