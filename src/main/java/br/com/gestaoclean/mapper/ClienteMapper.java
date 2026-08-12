package br.com.gestaoclean.mapper;

import br.com.gestaoclean.dto.ClienteResponseDTO;
import br.com.gestaoclean.dto.ClienteRequestDTO;
import br.com.gestaoclean.entity.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    public ClienteResponseDTO toDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .observacoes(cliente.getObservacoes())
                .ativo(cliente.getAtivo())
                .dataCadastro(cliente.getDataCadastro())
                .build();
    }

    public List<ClienteResponseDTO> toDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toDTO)
                .toList();
    }
    public Cliente toEntity(ClienteRequestDTO dto) {
        return Cliente.builder()
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .cpf(dto.getCpf())
                .observacoes(dto.getObservacoes())
                .ativo(true)
                .build();
    }
}