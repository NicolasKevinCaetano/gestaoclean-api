package br.com.gestaoclean.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import br.com.gestaoclean.entity.StatusOrdemServico;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoResponseDTO {

    private Long id;

    private Long agendamentoId;

    private Long clienteId;

    private String clienteNome;

    private LocalDateTime dataAgendamento;

    private LocalDateTime dataCriacao;

    private String observacoes;

    private List<ItemAgendamentoResponseDTO> itens;

    private BigDecimal valorTotal;

    private StatusOrdemServico status;
}