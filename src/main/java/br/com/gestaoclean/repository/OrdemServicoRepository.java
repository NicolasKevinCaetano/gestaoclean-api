package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    Optional<OrdemServico> findByAgendamentoId(Long agendamentoId);

    boolean existsByAgendamentoId(Long agendamentoId);
}