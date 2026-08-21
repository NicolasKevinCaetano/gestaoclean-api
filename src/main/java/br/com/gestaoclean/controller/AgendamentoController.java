package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.AgendamentoRequestDTO;
import br.com.gestaoclean.dto.AgendamentoResponseDTO;
import br.com.gestaoclean.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.gestaoclean.entity.StatusAgendamento;
import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> listarTodos(
            @RequestParam(required = false) StatusAgendamento status,
            @RequestParam(required = false) LocalDate data) {

        return ResponseEntity.ok(
                agendamentoService.filtrar(status, data)
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AgendamentoResponseDTO>> listarPorCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(
                agendamentoService.listarPorCliente(clienteId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> salvar(
            @Valid @RequestBody AgendamentoRequestDTO dto) {

        AgendamentoResponseDTO salvo = agendamentoService.salvar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody AgendamentoRequestDTO dto) {

        AgendamentoResponseDTO atualizado = agendamentoService.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        agendamentoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}