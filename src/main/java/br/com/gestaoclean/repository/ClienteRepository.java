package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}