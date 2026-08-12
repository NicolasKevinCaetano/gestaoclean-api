package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByAtivoTrue();
}