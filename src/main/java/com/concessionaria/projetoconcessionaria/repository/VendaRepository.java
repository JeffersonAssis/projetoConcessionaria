package com.concessionaria.projetoconcessionaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concessionaria.projetoconcessionaria.models.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
