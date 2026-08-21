package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.Agendamento;
import br.com.gestaoclean.entity.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByClienteId(Long clienteId);

    List<Agendamento> findByStatus(StatusAgendamento status);

    List<Agendamento> findByDataAgendamentoBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );

    List<Agendamento> findByStatusAndDataAgendamentoBetween(
            StatusAgendamento status,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    boolean existsByDataAgendamento(LocalDateTime dataAgendamento);

    boolean existsByDataAgendamentoAndIdNot(
            LocalDateTime dataAgendamento,
            Long id
    );
}