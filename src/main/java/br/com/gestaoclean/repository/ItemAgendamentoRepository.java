package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.ItemAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemAgendamentoRepository extends JpaRepository<ItemAgendamento, Long> {
}