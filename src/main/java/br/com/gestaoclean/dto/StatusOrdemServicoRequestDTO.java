package br.com.gestaoclean.dto;

import br.com.gestaoclean.entity.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusOrdemServicoRequestDTO {

    @NotNull
    private StatusOrdemServico status;
}
