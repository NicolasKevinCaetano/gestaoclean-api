package br.com.gestaoclean.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoRequestDTO {

    @NotNull
    private Long clienteId;

    @NotNull
    private LocalDateTime dataAgendamento;

    @NotNull(message = "Os itens do agendamento são obrigatórios")
    private List<@Valid ItemAgendamentoRequestDTO> itens;

    private String observacoes;
}