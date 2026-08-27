package br.com.gestaoclean.mapper;

import br.com.gestaoclean.dto.DespesaRequestDTO;
import br.com.gestaoclean.dto.DespesaResponseDTO;
import br.com.gestaoclean.entity.Despesa;

public class DespesaMapper {

    public static Despesa toEntity(DespesaRequestDTO dto) {

        return Despesa.builder()
                .valor(dto.getValor())
                .descricao(dto.getDescricao())
                .categoria(dto.getCategoria())
                .dataDespesa(dto.getDataDespesa())
                .observacoes(dto.getObservacoes())
                .build();
    }

    public static DespesaResponseDTO toResponseDTO(Despesa despesa) {

        return DespesaResponseDTO.builder()
                .id(despesa.getId())
                .valor(despesa.getValor())
                .descricao(despesa.getDescricao())
                .categoria(despesa.getCategoria())
                .dataDespesa(despesa.getDataDespesa())
                .observacoes(despesa.getObservacoes())
                .build();
    }
}
