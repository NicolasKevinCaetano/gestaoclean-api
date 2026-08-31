package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.ResumoFinanceiroDTO;
import br.com.gestaoclean.entity.StatusPagamento;
import br.com.gestaoclean.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FinanceiroService {

    private final PagamentoRepository pagamentoRepository;
    private final DespesaService despesaService;

    public ResumoFinanceiroDTO obterResumo() {

        BigDecimal totalRecebido =
                pagamentoRepository.somarPorStatus(StatusPagamento.PAGO);

        BigDecimal totalPendente =
                pagamentoRepository.somarPorStatus(StatusPagamento.PENDENTE);

        BigDecimal totalCancelado =
                pagamentoRepository.somarPorStatus(StatusPagamento.CANCELADO);

        long quantidadePagamentosPagos =
                pagamentoRepository.findByStatus(StatusPagamento.PAGO).size();

        long quantidadePagamentosPendentes =
                pagamentoRepository.findByStatus(StatusPagamento.PENDENTE).size();

        long quantidadePagamentosCancelados =
                pagamentoRepository.findByStatus(StatusPagamento.CANCELADO).size();

        return ResumoFinanceiroDTO.builder()
                .totalRecebido(totalRecebido)
                .totalPendente(totalPendente)
                .totalCancelado(totalCancelado)
                .quantidadePagamentosPagos(quantidadePagamentosPagos)
                .quantidadePagamentosPendentes(quantidadePagamentosPendentes)
                .quantidadePagamentosCancelados(quantidadePagamentosCancelados)
                .build();
    }

    public BigDecimal obterTotalRecebidoPorPeriodo(
            LocalDate inicio,
            LocalDate fim) {

        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime fimDateTime = fim.atTime(23, 59, 59);

        return pagamentoRepository.somarPorStatusEPeriodo(
                StatusPagamento.PAGO,
                inicioDateTime,
                fimDateTime
        );
    }

    public BigDecimal obterTotalDespesasPorPeriodo(
            LocalDate inicio,
            LocalDate fim) {

        return despesaService.listarPorPeriodo(inicio, fim)
                .stream()
                .map(despesa -> despesa.getValor())
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }

    public BigDecimal obterSaldoPorPeriodo(
            LocalDate inicio,
            LocalDate fim) {

        BigDecimal totalRecebido =
                obterTotalRecebidoPorPeriodo(inicio, fim);

        BigDecimal totalDespesas =
                obterTotalDespesasPorPeriodo(inicio, fim);

        return totalRecebido.subtract(totalDespesas);
    }

    public ResumoFinanceiroDTO obterResumoPorPeriodo(
            LocalDate inicio,
            LocalDate fim) {

        LocalDateTime inicioDateTime = inicio.atStartOfDay();
        LocalDateTime fimDateTime = fim.atTime(23, 59, 59);

        BigDecimal totalRecebido =
                pagamentoRepository.somarPorStatusEPeriodo(
                        StatusPagamento.PAGO,
                        inicioDateTime,
                        fimDateTime
                );

        BigDecimal totalPendente =
                pagamentoRepository.somarPorStatusEDataCriacao(
                        StatusPagamento.PENDENTE,
                        inicioDateTime,
                        fimDateTime
                );

        BigDecimal totalCancelado =
                pagamentoRepository.somarPorStatusEDataCancelamento(
                        StatusPagamento.CANCELADO,
                        inicioDateTime,
                        fimDateTime
                );

        long quantidadePagamentosPagos =
                pagamentoRepository
                        .findByStatusAndDataPagamentoBetween(
                                StatusPagamento.PAGO,
                                inicioDateTime,
                                fimDateTime
                        )
                        .size();

        long quantidadePagamentosPendentes =
                pagamentoRepository
                        .findByStatusAndDataCriacaoBetween(
                                StatusPagamento.PENDENTE,
                                inicioDateTime,
                                fimDateTime
                        )
                        .size();

        long quantidadePagamentosCancelados =
                pagamentoRepository
                        .findByStatusAndDataCancelamentoBetween(
                                StatusPagamento.CANCELADO,
                                inicioDateTime,
                                fimDateTime
                        )
                        .size();

        return ResumoFinanceiroDTO.builder()
                .totalRecebido(totalRecebido)
                .totalPendente(totalPendente)
                .totalCancelado(totalCancelado)
                .quantidadePagamentosPagos(quantidadePagamentosPagos)
                .quantidadePagamentosPendentes(quantidadePagamentosPendentes)
                .quantidadePagamentosCancelados(quantidadePagamentosCancelados)
                .build();
    }
}