package br.com.gestaoclean.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {

    private BigDecimal totalRecebido;

    private BigDecimal totalPendente;

    private BigDecimal totalDespesas;

    private BigDecimal saldo;

    private long quantidadePagamentosPagos;

    private long quantidadePagamentosPendentes;

    private long quantidadePagamentosCancelados;
}
