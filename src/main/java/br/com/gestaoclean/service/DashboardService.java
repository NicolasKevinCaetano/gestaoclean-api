package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.DashboardDTO;
import br.com.gestaoclean.dto.ResumoFinanceiroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FinanceiroService financeiroService;

    public DashboardDTO obterDashboard(
            LocalDate inicio,
            LocalDate fim) {

        ResumoFinanceiroDTO resumo =
                financeiroService.obterResumoPorPeriodo(
                        inicio,
                        fim
                );

        BigDecimal totalDespesas =
                financeiroService.obterTotalDespesasPorPeriodo(
                        inicio,
                        fim
                );

        BigDecimal saldo =
                resumo.getTotalRecebido()
                        .subtract(totalDespesas);

        return DashboardDTO.builder()
                .totalRecebido(resumo.getTotalRecebido())
                .totalPendente(resumo.getTotalPendente())
                .totalDespesas(totalDespesas)
                .saldo(saldo)
                .quantidadePagamentosPagos(
                        resumo.getQuantidadePagamentosPagos()
                )
                .quantidadePagamentosPendentes(
                        resumo.getQuantidadePagamentosPendentes()
                )
                .quantidadePagamentosCancelados(
                        resumo.getQuantidadePagamentosCancelados()
                )
                .build();
    }
}
