package com.concessionaria.projetoconcessionaria.repository;

import com.concessionaria.projetoconcessionaria.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}
