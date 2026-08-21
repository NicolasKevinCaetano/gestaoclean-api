package br.com.gestaoclean.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoResponseDTO {

    private Long id;

    private Long agendamentoId;

    private LocalDateTime dataCriacao;

    private String observacoes;
}
