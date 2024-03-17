package com.concessionaria.projetoconcessionaria.repository;

import com.concessionaria.projetoconcessionaria.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.concessionaria.projetoconcessionaria.models.Cliente;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    @Query("From Cliente c where c.cpf = :cpf")
    Optional<Cliente> getCliente(@Param("cpf") String cpf);

}
