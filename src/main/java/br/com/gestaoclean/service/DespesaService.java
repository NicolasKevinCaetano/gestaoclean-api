package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.DespesaRequestDTO;
import br.com.gestaoclean.dto.DespesaResponseDTO;
import br.com.gestaoclean.entity.Despesa;
import br.com.gestaoclean.mapper.DespesaMapper;
import br.com.gestaoclean.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;

    public DespesaResponseDTO criar(DespesaRequestDTO dto) {

        Despesa despesa = DespesaMapper.toEntity(dto);

        return DespesaMapper.toResponseDTO(
                despesaRepository.save(despesa)
        );
    }

    public List<DespesaResponseDTO> listar() {

        return despesaRepository.findAll()
                .stream()
                .map(DespesaMapper::toResponseDTO)
                .toList();
    }

    public DespesaResponseDTO buscarPorId(Long id) {

        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Despesa não encontrada"));

        return DespesaMapper.toResponseDTO(despesa);
    }

    public List<DespesaResponseDTO> listarPorCategoria(
            String categoria) {

        return despesaRepository.findByCategoria(categoria)
                .stream()
                .map(DespesaMapper::toResponseDTO)
                .toList();
    }

    public List<DespesaResponseDTO> listarPorPeriodo(
            LocalDate inicio,
            LocalDate fim) {

        LocalDateTime inicioDateTime =
                inicio.atStartOfDay();

        LocalDateTime fimDateTime =
                fim.atTime(23, 59, 59);

        return despesaRepository
                .findByDataDespesaBetween(
                        inicioDateTime,
                        fimDateTime
                )
                .stream()
                .map(DespesaMapper::toResponseDTO)
                .toList();
    }

    public DespesaResponseDTO atualizar(
            Long id,
            DespesaRequestDTO dto) {

        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Despesa não encontrada"));

        despesa.setValor(dto.getValor());
        despesa.setDescricao(dto.getDescricao());
        despesa.setCategoria(dto.getCategoria());
        despesa.setDataDespesa(dto.getDataDespesa());
        despesa.setObservacoes(dto.getObservacoes());

        return DespesaMapper.toResponseDTO(
                despesaRepository.save(despesa)
        );
    }

    public void excluir(Long id) {

        Despesa despesa = despesaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Despesa não encontrada"));

        despesaRepository.delete(despesa);
    }
}
