package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.PagamentoRequestDTO;
import br.com.gestaoclean.dto.PagamentoResponseDTO;
import br.com.gestaoclean.entity.OrdemServico;
import br.com.gestaoclean.entity.Pagamento;
import br.com.gestaoclean.entity.StatusPagamento;
import br.com.gestaoclean.entity.StatusOrdemServico;
import br.com.gestaoclean.mapper.PagamentoMapper;
import br.com.gestaoclean.repository.OrdemServicoRepository;
import br.com.gestaoclean.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final OrdemServicoRepository ordemServicoRepository;

    public PagamentoResponseDTO criar(
            Long ordemServicoId,
            PagamentoRequestDTO dto) {

        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() ->
                        new RuntimeException("Ordem de Serviço não encontrada"));

        if (ordemServico.getStatus() == StatusOrdemServico.CANCELADA) {
            throw new RuntimeException(
                    "Não é possível criar pagamento para uma Ordem de Serviço cancelada");
        }

        Pagamento pagamento = PagamentoMapper.toEntity(dto, ordemServico);

        pagamento.setStatus(StatusPagamento.PENDENTE);
        pagamento.setDataPagamento(null);
        pagamento.setDataCancelamento(null);

        pagamento = pagamentoRepository.save(pagamento);

        return PagamentoMapper.toResponseDTO(pagamento);
    }

    public List<PagamentoResponseDTO> listarPorOrdemServico(
            Long ordemServicoId) {

        if (!ordemServicoRepository.existsById(ordemServicoId)) {
            throw new RuntimeException("Ordem de Serviço não encontrada");
        }

        return pagamentoRepository.findByOrdemServicoId(ordemServicoId)
                .stream()
                .map(PagamentoMapper::toResponseDTO)
                .toList();
    }

    public List<PagamentoResponseDTO> listarPorStatus(
            StatusPagamento status) {

        return pagamentoRepository.findByStatus(status)
                .stream()
                .map(PagamentoMapper::toResponseDTO)
                .toList();
    }

    public PagamentoResponseDTO buscarPorId(Long id) {

        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pagamento não encontrado"));

        return PagamentoMapper.toResponseDTO(pagamento);
    }

    public PagamentoResponseDTO marcarComoPago(Long id) {

        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pagamento não encontrado"));

        if (pagamento.getStatus() == StatusPagamento.CANCELADO) {
            throw new RuntimeException(
                    "Pagamento cancelado não pode ser marcado como pago");
        }

        if (pagamento.getStatus() == StatusPagamento.PAGO) {
            throw new RuntimeException(
                    "Pagamento já está pago");
        }

        pagamento.setStatus(StatusPagamento.PAGO);
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setDataCancelamento(null);

        pagamento = pagamentoRepository.save(pagamento);

        return PagamentoMapper.toResponseDTO(pagamento);
    }

    public PagamentoResponseDTO cancelar(Long id) {

        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pagamento não encontrado"));

        if (pagamento.getStatus() == StatusPagamento.CANCELADO) {
            throw new RuntimeException(
                    "Pagamento já está cancelado");
        }

        if (pagamento.getStatus() == StatusPagamento.PAGO) {
            throw new RuntimeException(
                    "Pagamento pago não pode ser cancelado");
        }

        pagamento.setStatus(StatusPagamento.CANCELADO);
        pagamento.setDataCancelamento(LocalDateTime.now());

        pagamento = pagamentoRepository.save(pagamento);

        return PagamentoMapper.toResponseDTO(pagamento);
    }
}

