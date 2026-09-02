package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.UsuarioRequestDTO;
import br.com.gestaoclean.entity.PerfilUsuario;
import br.com.gestaoclean.entity.Usuario;
import br.com.gestaoclean.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void registrar(UsuarioRequestDTO dto) {

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .perfil(PerfilUsuario.USER)
                .ativo(true)
                .build();

        usuarioRepository.save(usuario);
    }
}