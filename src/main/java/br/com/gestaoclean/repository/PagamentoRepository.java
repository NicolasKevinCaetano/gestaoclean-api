package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.Pagamento;
import br.com.gestaoclean.entity.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByOrdemServicoId(Long ordemServicoId);

    List<Pagamento> findByStatus(StatusPagamento status);

    @Query("""
            SELECT COALESCE(SUM(p.valor), 0)
            FROM Pagamento p
            WHERE p.status = :status
            """)
    BigDecimal somarPorStatus(StatusPagamento status);
}




