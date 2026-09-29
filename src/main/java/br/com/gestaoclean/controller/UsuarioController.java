package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.UsuarioCadastroRequestDTO;
import br.com.gestaoclean.dto.UsuarioResponseDTO;
import br.com.gestaoclean.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import br.com.gestaoclean.dto.UsuarioAtualizacaoRequestDTO;
import br.com.gestaoclean.dto.UsuarioSenhaRequestDTO;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarFuncionario(
            @Valid @RequestBody UsuarioCadastroRequestDTO dto) {

        UsuarioResponseDTO usuario =
                usuarioService.cadastrarFuncionario(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {

        return ResponseEntity.ok(
                usuarioService.listarTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.buscarPorId(id)
        );
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<UsuarioResponseDTO> desativar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.desativar(id)
        );
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<UsuarioResponseDTO> reativar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                usuarioService.reativar(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioAtualizacaoRequestDTO dto) {

        return ResponseEntity.ok(
                usuarioService.atualizar(id, dto)
        );
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioSenhaRequestDTO dto) {

        usuarioService.alterarSenha(id, dto);

        return ResponseEntity.noContent().build();
    }
}
