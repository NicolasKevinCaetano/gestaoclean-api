package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.Pagamento;
import br.com.gestaoclean.entity.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByOrdemServicoId(Long ordemServicoId);

    List<Pagamento> findByStatus(StatusPagamento status);

    List<Pagamento> findByStatusAndDataPagamentoBetween(
            StatusPagamento status,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    List<Pagamento> findByStatusAndDataCancelamentoBetween(
            StatusPagamento status,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    @Query("""
            SELECT COALESCE(SUM(p.valor), 0)
            FROM Pagamento p
            WHERE p.status = :status
            """)
    BigDecimal somarPorStatus(StatusPagamento status);

    @Query("""
            SELECT COALESCE(SUM(p.valor), 0)
            FROM Pagamento p
            WHERE p.status = :status
            AND p.dataPagamento BETWEEN :inicio AND :fim
            """)
    BigDecimal somarPorStatusEPeriodo(
            @Param("status") StatusPagamento status,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}




