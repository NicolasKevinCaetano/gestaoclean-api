package br.com.gestaoclean.mapper;

import br.com.gestaoclean.dto.ItemAgendamentoResponseDTO;
import br.com.gestaoclean.entity.ItemAgendamento;

public class ItemAgendamentoMapper {

    public static ItemAgendamentoResponseDTO toResponseDTO(ItemAgendamento item) {

        return ItemAgendamentoResponseDTO.builder()
                .id(item.getId())
                .servico(item.getServico())
                .valor(item.getValor())
                .build();
    }
}