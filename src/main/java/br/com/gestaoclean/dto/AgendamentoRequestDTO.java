package br.com.gestaoclean.dto;

import br.com.gestaoclean.entity.StatusAgendamento;
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

    @NotNull
    private StatusAgendamento status;

    private String observacoes;
}