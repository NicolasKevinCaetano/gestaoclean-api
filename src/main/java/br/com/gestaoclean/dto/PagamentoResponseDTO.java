package br.com.gestaoclean.dto;

import br.com.gestaoclean.entity.StatusPagamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoResponseDTO {

    private Long id;

    private Long ordemServicoId;

    private Long clienteId;

    private String clienteNome;

    private BigDecimal valor;

    private StatusPagamento status;

    private LocalDateTime dataPagamento;

    private String formaPagamento;

    private String observacoes;
}


