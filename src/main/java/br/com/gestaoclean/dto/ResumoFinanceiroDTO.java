package br.com.gestaoclean.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumoFinanceiroDTO {

    private BigDecimal totalRecebido;

    private BigDecimal totalPendente;

    private BigDecimal totalCancelado;

    private long quantidadePagamentosPagos;

    private long quantidadePagamentosPendentes;

    private long quantidadePagamentosCancelados;
}


