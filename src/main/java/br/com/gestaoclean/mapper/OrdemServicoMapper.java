package br.com.gestaoclean.mapper;

import br.com.gestaoclean.dto.OrdemServicoRequestDTO;
import br.com.gestaoclean.dto.OrdemServicoResponseDTO;
import br.com.gestaoclean.entity.OrdemServico;
import org.springframework.stereotype.Component;

@Component
public class OrdemServicoMapper {

    public OrdemServicoResponseDTO toResponseDTO(OrdemServico ordemServico) {

        return OrdemServicoResponseDTO.builder()
                .id(ordemServico.getId())
                .agendamentoId(ordemServico.getAgendamento().getId())
                .dataCriacao(ordemServico.getDataCriacao())
                .observacoes(ordemServico.getObservacoes())
                .build();
    }
}