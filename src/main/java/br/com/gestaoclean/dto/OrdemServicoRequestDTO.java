package br.com.gestaoclean.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoRequestDTO {

    @NotNull
    private Long agendamentoId;

    private String observacoes;
}