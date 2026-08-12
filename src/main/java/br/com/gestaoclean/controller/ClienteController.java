package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.ClienteResponseDTO;
import br.com.gestaoclean.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import br.com.gestaoclean.dto.ClienteRequestDTO;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteResponseDTO> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    public ClienteResponseDTO salvar(@Valid @RequestBody ClienteRequestDTO dto) {
        return clienteService.salvar(dto);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto) {

        return clienteService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }
}