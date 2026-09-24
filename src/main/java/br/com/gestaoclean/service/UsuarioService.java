package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.UsuarioCadastroRequestDTO;
import br.com.gestaoclean.dto.UsuarioResponseDTO;
import br.com.gestaoclean.entity.Usuario;
import br.com.gestaoclean.enums.PerfilUsuario;
import br.com.gestaoclean.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import br.com.gestaoclean.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO cadastrarFuncionario(
            UsuarioCadastroRequestDTO dto) {

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalStateException("E-mail já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .perfil(PerfilUsuario.FUNCIONARIO)
                .ativo(true)
                .build();

        Usuario salvo = usuarioRepository.save(usuario);

        return toResponseDTO(salvo);
    }

    public List<UsuarioResponseDTO> listarTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO buscarPorId(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado")
                );

        return toResponseDTO(usuario);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {

        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil())
                .ativo(usuario.getAtivo())
                .build();
    }

    public UsuarioResponseDTO desativar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado")
                );

        if (usuario.getPerfil() == PerfilUsuario.ADMIN) {
            throw new IllegalStateException(
                    "Administrador não pode ser desativado por este recurso"
            );
        }

        if (!usuario.getAtivo()) {
            throw new IllegalStateException("Usuário já está inativo");
        }

        usuario.setAtivo(false);

        Usuario salvo = usuarioRepository.save(usuario);

        return toResponseDTO(salvo);
    }

    public UsuarioResponseDTO reativar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado")
                );

        if (usuario.getPerfil() == PerfilUsuario.ADMIN) {
            throw new IllegalStateException(
                    "Administrador não pode ser reativado por este recurso"
            );
        }

        if (usuario.getAtivo()) {
            throw new IllegalStateException("Usuário já está ativo");
        }

        usuario.setAtivo(true);

        Usuario salvo = usuarioRepository.save(usuario);

        return toResponseDTO(salvo);
    }
}
