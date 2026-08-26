package br.com.gestaoclean.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoRequestDTO {

    @NotNull
    @Positive
    private BigDecimal valor;

    private String formaPagamento;

    private String observacoes;
}

