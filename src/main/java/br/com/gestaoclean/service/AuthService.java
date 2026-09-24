package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.LoginRequestDTO;
import br.com.gestaoclean.dto.LoginResponseDTO;
import br.com.gestaoclean.entity.Usuario;
import br.com.gestaoclean.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.gestaoclean.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public LoginResponseDTO login(LoginRequestDTO dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getSenha()
                )
        );

        String token = jwtService.gerarToken(dto.getEmail());

        return LoginResponseDTO.builder()
                .mensagem("Login realizado com sucesso")
                .token(token)
                .build();
    }
}