package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.ResumoFinanceiroDTO;
import br.com.gestaoclean.entity.StatusPagamento;
import br.com.gestaoclean.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FinanceiroService {

    private final PagamentoRepository pagamentoRepository;

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
}
