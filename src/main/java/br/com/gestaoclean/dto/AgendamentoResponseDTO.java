package br.com.gestaoclean.dto;

import br.com.gestaoclean.entity.StatusAgendamento;
import lombok.*;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgendamentoResponseDTO {

    private Long id;
    private Long clienteId;
    private String clienteNome;
    private LocalDateTime dataAgendamento;
    private StatusAgendamento status;
    private String observacoes;
    private List<ItemAgendamentoResponseDTO> itens;
    private BigDecimal valorTotal;
}