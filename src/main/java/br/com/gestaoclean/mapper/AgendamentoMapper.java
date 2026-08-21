package br.com.gestaoclean.mapper;

import br.com.gestaoclean.dto.AgendamentoRequestDTO;
import br.com.gestaoclean.dto.AgendamentoResponseDTO;
import br.com.gestaoclean.dto.ItemAgendamentoResponseDTO;
import br.com.gestaoclean.entity.Agendamento;
import br.com.gestaoclean.entity.Cliente;
import br.com.gestaoclean.entity.ItemAgendamento;

import java.math.BigDecimal;
import java.util.List;

public class AgendamentoMapper {

    public static Agendamento toEntity(AgendamentoRequestDTO dto, Cliente cliente) {

        Agendamento agendamento = Agendamento.builder()
                .cliente(cliente)
                .dataAgendamento(dto.getDataAgendamento())
                .status(dto.getStatus())
                .observacoes(dto.getObservacoes())
                .build();

        if (dto.getItens() != null) {
            dto.getItens().forEach(itemDTO -> {

                ItemAgendamento item = ItemAgendamento.builder()
                        .agendamento(agendamento)
                        .servico(itemDTO.getServico())
                        .valor(itemDTO.getValor())
                        .build();

                agendamento.getItens().add(item);
            });
        }

        return agendamento;
    }

    public static AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {

        BigDecimal valorTotal = agendamento.getItens()
                .stream()
                .map(ItemAgendamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return AgendamentoResponseDTO.builder()
                .id(agendamento.getId())
                .clienteId(agendamento.getCliente().getId())
                .clienteNome(agendamento.getCliente().getNome())
                .dataAgendamento(agendamento.getDataAgendamento())
                .status(agendamento.getStatus())
                .observacoes(agendamento.getObservacoes())
                .itens(
                        agendamento.getItens()
                                .stream()
                                .map(ItemAgendamentoMapper::toResponseDTO)
                                .toList()
                )
                .valorTotal(valorTotal)
                .build();
    }
}