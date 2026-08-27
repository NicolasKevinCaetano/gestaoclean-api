package br.com.gestaoclean.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DespesaResponseDTO {

    private Long id;

    private BigDecimal valor;

    private String descricao;

    private String categoria;

    private LocalDateTime dataDespesa;

    private String observacoes;
}
