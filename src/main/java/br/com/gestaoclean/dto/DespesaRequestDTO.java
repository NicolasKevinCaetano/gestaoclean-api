package br.com.gestaoclean.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DespesaRequestDTO {

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotBlank
    private String descricao;

    @NotBlank
    private String categoria;

    @NotNull
    private LocalDateTime dataDespesa;

    private String observacoes;
}