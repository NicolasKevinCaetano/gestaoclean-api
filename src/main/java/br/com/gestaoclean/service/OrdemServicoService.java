package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.OrdemServicoRequestDTO;
import br.com.gestaoclean.dto.OrdemServicoResponseDTO;
import br.com.gestaoclean.entity.Agendamento;
import br.com.gestaoclean.entity.OrdemServico;
import br.com.gestaoclean.exception.ResourceNotFoundException;
import br.com.gestaoclean.mapper.OrdemServicoMapper;
import br.com.gestaoclean.repository.AgendamentoRepository;
import br.com.gestaoclean.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final OrdemServicoMapper ordemServicoMapper;

    public List<OrdemServicoResponseDTO> listarTodos() {

        return ordemServicoRepository.findAll()
                .stream()
                .map(ordemServicoMapper::toResponseDTO)
                .toList();
    }

    public OrdemServicoResponseDTO buscarPorId(Long id) {

        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ordem de Serviço não encontrada"));

        return ordemServicoMapper.toResponseDTO(ordemServico);
    }

    public OrdemServicoResponseDTO criar(OrdemServicoRequestDTO dto) {

        if (ordemServicoRepository.existsByAgendamentoId(dto.getAgendamentoId())) {
            throw new IllegalStateException(
                    "Já existe uma Ordem de Serviço para este agendamento"
            );
        }

        Agendamento agendamento = agendamentoRepository.findById(dto.getAgendamentoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agendamento não encontrado"));

        OrdemServico ordemServico = OrdemServico.builder()
                .agendamento(agendamento)
                .dataCriacao(LocalDateTime.now())
                .observacoes(dto.getObservacoes())
                .build();

        OrdemServico salva = ordemServicoRepository.save(ordemServico);

        return ordemServicoMapper.toResponseDTO(salva);
    }
}
