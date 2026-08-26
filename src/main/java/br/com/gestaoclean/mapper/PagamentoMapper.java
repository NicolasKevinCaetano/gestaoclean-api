package br.com.gestaoclean.mapper;

import br.com.gestaoclean.dto.PagamentoRequestDTO;
import br.com.gestaoclean.dto.PagamentoResponseDTO;
import br.com.gestaoclean.entity.Pagamento;
import br.com.gestaoclean.entity.OrdemServico;

public class PagamentoMapper {

    public static Pagamento toEntity(
            PagamentoRequestDTO dto,
            OrdemServico ordemServico) {

        return Pagamento.builder()
                .ordemServico(ordemServico)
                .valor(dto.getValor())
                .formaPagamento(dto.getFormaPagamento())
                .observacoes(dto.getObservacoes())
                .build();
    }

    public static PagamentoResponseDTO toResponseDTO(
            Pagamento pagamento) {

        OrdemServico ordemServico = pagamento.getOrdemServico();

        return PagamentoResponseDTO.builder()
                .id(pagamento.getId())
                .ordemServicoId(ordemServico.getId())
                .clienteId(ordemServico.getAgendamento().getCliente().getId())
                .clienteNome(ordemServico.getAgendamento().getCliente().getNome())
                .valor(pagamento.getValor())
                .status(pagamento.getStatus())
                .dataPagamento(pagamento.getDataPagamento())
                .formaPagamento(pagamento.getFormaPagamento())
                .observacoes(pagamento.getObservacoes())
                .build();
    }
}


