package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.DespesaRequestDTO;
import br.com.gestaoclean.dto.DespesaResponseDTO;
import br.com.gestaoclean.service.DespesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @PostMapping
    public ResponseEntity<DespesaResponseDTO> criar(
            @Valid @RequestBody DespesaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(despesaService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<DespesaResponseDTO>> listar() {

        return ResponseEntity.ok(
                despesaService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                despesaService.buscarPorId(id)
        );
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<DespesaResponseDTO>> listarPorCategoria(
            @PathVariable String categoria) {

        return ResponseEntity.ok(
                despesaService.listarPorCategoria(categoria)
        );
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<DespesaResponseDTO>> listarPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate inicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fim) {

        return ResponseEntity.ok(
                despesaService.listarPorPeriodo(inicio, fim)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DespesaRequestDTO dto) {

        return ResponseEntity.ok(
                despesaService.atualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        despesaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
