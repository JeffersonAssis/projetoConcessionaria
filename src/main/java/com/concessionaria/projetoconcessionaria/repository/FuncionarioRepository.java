package com.concessionaria.projetoconcessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.concessionaria.projetoconcessionaria.models.Funcionario;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query("from Funcionario f where f.matricula = :matricula")
    Optional<Funcionario> getFunMatricula(String matricula);

}
