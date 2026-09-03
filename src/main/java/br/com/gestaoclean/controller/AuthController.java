package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.LoginRequestDTO;
import br.com.gestaoclean.dto.LoginResponseDTO;
import br.com.gestaoclean.dto.UsuarioRequestDTO;
import br.com.gestaoclean.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(
            @Valid @RequestBody UsuarioRequestDTO dto) {

        authService.registrar(dto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {

        return ResponseEntity.ok(authService.login(dto));
    }
}