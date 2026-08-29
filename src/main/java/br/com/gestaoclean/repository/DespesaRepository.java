package br.com.gestaoclean.repository;

import br.com.gestaoclean.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findByDataDespesaBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );

    List<Despesa> findByCategoria(String categoria);

    @Query("""
            SELECT COALESCE(SUM(d.valor), 0)
            FROM Despesa d
            """)
    BigDecimal somarTodas();
}