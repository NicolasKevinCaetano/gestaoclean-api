package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.PagamentoRequestDTO;
import br.com.gestaoclean.dto.PagamentoResponseDTO;
import br.com.gestaoclean.entity.StatusPagamento;
import br.com.gestaoclean.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping("/ordem-servico/{ordemServicoId}")
    public ResponseEntity<PagamentoResponseDTO> criar(
            @PathVariable Long ordemServicoId,
            @Valid @RequestBody PagamentoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pagamentoService.criar(ordemServicoId, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pagamentoService.buscarPorId(id)
        );
    }

    @GetMapping("/ordem-servico/{ordemServicoId}")
    public ResponseEntity<List<PagamentoResponseDTO>> listarPorOrdemServico(
            @PathVariable Long ordemServicoId) {

        return ResponseEntity.ok(
                pagamentoService.listarPorOrdemServico(ordemServicoId)
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PagamentoResponseDTO>> listarPorStatus(
            @PathVariable StatusPagamento status) {

        return ResponseEntity.ok(
                pagamentoService.listarPorStatus(status)
        );
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<PagamentoResponseDTO> marcarComoPago(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pagamentoService.marcarComoPago(id)
        );
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<PagamentoResponseDTO> cancelar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pagamentoService.cancelar(id)
        );
    }
}