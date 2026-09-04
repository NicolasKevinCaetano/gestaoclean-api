package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.AgendamentoRequestDTO;
import br.com.gestaoclean.dto.AgendamentoResponseDTO;
import br.com.gestaoclean.entity.Agendamento;
import br.com.gestaoclean.entity.Cliente;
import br.com.gestaoclean.exception.ResourceNotFoundException;
import br.com.gestaoclean.mapper.AgendamentoMapper;
import br.com.gestaoclean.repository.AgendamentoRepository;
import br.com.gestaoclean.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import br.com.gestaoclean.entity.StatusAgendamento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import br.com.gestaoclean.entity.ItemAgendamento;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;

    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            ClienteRepository clienteRepository) {

        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<AgendamentoResponseDTO> listarTodos() {
        return agendamentoRepository.findAll()
                .stream()
                .map(AgendamentoMapper::toResponseDTO)
                .toList();
    }

    public AgendamentoResponseDTO buscarPorId(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agendamento não encontrado"));

        return AgendamentoMapper.toResponseDTO(agendamento);
    }

    public AgendamentoResponseDTO salvar(AgendamentoRequestDTO dto) {

        if (agendamentoRepository.existsByDataAgendamento(dto.getDataAgendamento())) {
            throw new IllegalStateException(
                    "Já existe um agendamento para este horário");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado"));

        Agendamento agendamento = AgendamentoMapper.toEntity(dto, cliente);

        Agendamento salvo = agendamentoRepository.save(agendamento);

        return AgendamentoMapper.toResponseDTO(salvo);
    }

    public AgendamentoResponseDTO atualizar(Long id, AgendamentoRequestDTO dto) {

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agendamento não encontrado"));

        validarTransicaoStatus(
                agendamento.getStatus(),
                dto.getStatus()
        );

        if (agendamentoRepository.existsByDataAgendamentoAndIdNot(
                dto.getDataAgendamento(),
                id)) {

            throw new IllegalStateException(
                    "Já existe um agendamento para este horário");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado"));


        agendamento.setCliente(cliente);
        agendamento.setDataAgendamento(dto.getDataAgendamento());

        agendamento.getItens().clear();

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


        agendamento.setStatus(dto.getStatus());
        agendamento.setObservacoes(dto.getObservacoes());

        Agendamento atualizado = agendamentoRepository.save(agendamento);

        return AgendamentoMapper.toResponseDTO(atualizado);
    }

    public List<AgendamentoResponseDTO> filtrar(
            StatusAgendamento status,
            LocalDate data) {

        List<Agendamento> agendamentos;

        if (status != null && data != null) {

            LocalDateTime inicio = data.atStartOfDay();
            LocalDateTime fim = data.plusDays(1).atStartOfDay();

            agendamentos = agendamentoRepository
                    .findByStatusAndDataAgendamentoBetween(
                            status,
                            inicio,
                            fim
                    );

        } else if (status != null) {

            agendamentos = agendamentoRepository
                    .findByStatus(status);

        } else if (data != null) {

            LocalDateTime inicio = data.atStartOfDay();
            LocalDateTime fim = data.plusDays(1).atStartOfDay();

            agendamentos = agendamentoRepository
                    .findByDataAgendamentoBetween(
                            inicio,
                            fim
                    );

        } else {

            agendamentos = agendamentoRepository.findAll();
        }

        return agendamentos.stream()
                .map(AgendamentoMapper::toResponseDTO)
                .toList();
    }


    public void excluir(Long id) {

        if (!agendamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado");
        }

        agendamentoRepository.deleteById(id);
    }

    public List<AgendamentoResponseDTO> listarPorCliente(Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        return agendamentoRepository.findByClienteId(clienteId)
                .stream()
                .map(AgendamentoMapper::toResponseDTO)
                .toList();
    }

    public AgendamentoResponseDTO cancelar(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agendamento não encontrado"));

        if (agendamento.getStatus() == StatusAgendamento.REALIZADO) {
            throw new IllegalStateException(
                    "Agendamento realizado não pode ser cancelado");
        }

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new IllegalStateException(
                    "Agendamento já está cancelado");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);

        Agendamento atualizado = agendamentoRepository.save(agendamento);

        return AgendamentoMapper.toResponseDTO(atualizado);
    }

    private void validarTransicaoStatus(
            StatusAgendamento statusAtual,
            StatusAgendamento novoStatus) {

        if (statusAtual == StatusAgendamento.REALIZADO) {
            throw new IllegalStateException(
                    "Agendamento realizado não pode ter o status alterado");
        }

        if (statusAtual == StatusAgendamento.CANCELADO) {
            throw new IllegalStateException(
                    "Agendamento cancelado não pode ter o status alterado");
        }

        if (statusAtual == StatusAgendamento.AGENDADO &&
                novoStatus == StatusAgendamento.REALIZADO) {

            throw new IllegalStateException(
                    "Um agendamento deve ser confirmado antes de ser realizado");
        }
    }
}