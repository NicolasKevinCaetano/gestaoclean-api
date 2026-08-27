package br.com.gestaoclean.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumoFinanceiroPeriodoDTO {

    private BigDecimal totalRecebido;

    private BigDecimal totalDespesas;

    private BigDecimal saldo;
}
