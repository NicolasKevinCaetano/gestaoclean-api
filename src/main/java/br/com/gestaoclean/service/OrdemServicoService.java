package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.OrdemServicoRequestDTO;
import br.com.gestaoclean.dto.OrdemServicoResponseDTO;
import br.com.gestaoclean.entity.Agendamento;
import br.com.gestaoclean.entity.OrdemServico;
import br.com.gestaoclean.entity.StatusAgendamento;
import br.com.gestaoclean.entity.StatusOrdemServico;
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

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new IllegalStateException(
                    "Não é possível criar uma Ordem de Serviço para um agendamento cancelado"
            );
        }

        OrdemServico ordemServico = OrdemServico.builder()
                .agendamento(agendamento)
                .dataCriacao(LocalDateTime.now())
                .status(StatusOrdemServico.ABERTA)
                .observacoes(dto.getObservacoes())
                .build();

        OrdemServico salva = ordemServicoRepository.save(ordemServico);

        return ordemServicoMapper.toResponseDTO(salva);
    }

    public OrdemServicoResponseDTO atualizarStatus(
            Long id,
            StatusOrdemServico novoStatus) {

        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ordem de Serviço não encontrada"));

        StatusOrdemServico statusAtual = ordemServico.getStatus();

        if (statusAtual == StatusOrdemServico.FINALIZADA) {
            throw new IllegalStateException(
                    "Ordem de Serviço finalizada não pode ter o status alterado");
        }

        if (statusAtual == StatusOrdemServico.CANCELADA) {
            throw new IllegalStateException(
                    "Ordem de Serviço cancelada não pode ter o status alterado");
        }

        if (statusAtual == StatusOrdemServico.ABERTA
                && novoStatus != StatusOrdemServico.EM_EXECUCAO
                && novoStatus != StatusOrdemServico.CANCELADA) {

            throw new IllegalStateException(
                    "Ordem de Serviço ABERTA só pode ser colocada em execução ou cancelada");
        }

        if (statusAtual == StatusOrdemServico.EM_EXECUCAO
                && novoStatus != StatusOrdemServico.FINALIZADA
                && novoStatus != StatusOrdemServico.CANCELADA) {

            throw new IllegalStateException(
                    "Ordem de Serviço em execução só pode ser finalizada ou cancelada");
        }

        ordemServico.setStatus(novoStatus);

        OrdemServico salva = ordemServicoRepository.save(ordemServico);

        return ordemServicoMapper.toResponseDTO(salva);
    }

    public OrdemServicoResponseDTO buscarPorAgendamento(Long agendamentoId) {

        OrdemServico ordemServico = ordemServicoRepository
                .findByAgendamentoId(agendamentoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Ordem de Serviço não encontrada para este agendamento"));

        return ordemServicoMapper.toResponseDTO(ordemServico);
    }

    public List<OrdemServicoResponseDTO> listarPorStatus(
            StatusOrdemServico status) {

        return ordemServicoRepository.findByStatus(status)
                .stream()
                .map(ordemServicoMapper::toResponseDTO)
                .toList();
    }

}
