package com.concessionaria.projetoconcessionaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.concessionaria.projetoconcessionaria.models.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

   @Query(value = "From Veiculo v where v.placa = :placa")
   Optional<Veiculo> buscarPorPlaca(@Param("placa") String placa);

}
