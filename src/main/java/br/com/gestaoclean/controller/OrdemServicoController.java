package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.OrdemServicoRequestDTO;
import br.com.gestaoclean.dto.OrdemServicoResponseDTO;
import br.com.gestaoclean.service.OrdemServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    @GetMapping
    public ResponseEntity<List<OrdemServicoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(ordemServicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(ordemServicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDTO> criar(
            @Valid @RequestBody OrdemServicoRequestDTO dto) {

        OrdemServicoResponseDTO response =
                ordemServicoService.criar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
