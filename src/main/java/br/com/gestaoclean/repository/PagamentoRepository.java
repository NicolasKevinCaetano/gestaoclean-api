package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.Pagamento;
import br.com.gestaoclean.entity.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByOrdemServicoId(Long ordemServicoId);

    List<Pagamento> findByStatus(StatusPagamento status);
}

