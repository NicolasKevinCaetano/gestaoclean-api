package br.com.gestaoclean.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemAgendamentoResponseDTO {

    private Long id;

    private String servico;

    private BigDecimal valor;
}