package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.ClienteRequestDTO;
import br.com.gestaoclean.dto.ClienteResponseDTO;
import br.com.gestaoclean.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(
            @Valid @RequestBody ClienteRequestDTO dto) {

        ClienteResponseDTO salvo = clienteService.salvar(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto) {

        ClienteResponseDTO atualizado =
                clienteService.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}