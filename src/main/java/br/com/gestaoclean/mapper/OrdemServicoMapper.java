package br.com.gestaoclean.mapper;

import br.com.gestaoclean.dto.ItemAgendamentoResponseDTO;
import br.com.gestaoclean.dto.OrdemServicoResponseDTO;
import br.com.gestaoclean.entity.OrdemServico;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class OrdemServicoMapper {

    public OrdemServicoResponseDTO toResponseDTO(OrdemServico ordemServico) {

        var agendamento = ordemServico.getAgendamento();

        var itens = agendamento.getItens()
                .stream()
                .map(item -> ItemAgendamentoResponseDTO.builder()
                        .id(item.getId())
                        .servico(item.getServico())
                        .valor(item.getValor())
                        .build())
                .collect(Collectors.toList());

        var valorTotal = itens.stream()
                .map(ItemAgendamentoResponseDTO::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return OrdemServicoResponseDTO.builder()
                .id(ordemServico.getId())
                .agendamentoId(agendamento.getId())
                .clienteId(agendamento.getCliente().getId())
                .clienteNome(agendamento.getCliente().getNome())
                .dataAgendamento(agendamento.getDataAgendamento())
                .dataCriacao(ordemServico.getDataCriacao())
                .status(ordemServico.getStatus())
                .observacoes(ordemServico.getObservacoes())
                .itens(itens)
                .valorTotal(valorTotal)
                .build();
    }
}