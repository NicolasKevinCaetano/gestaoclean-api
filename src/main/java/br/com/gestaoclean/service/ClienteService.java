package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.ClienteResponseDTO;
import br.com.gestaoclean.dto.ClienteRequestDTO;
import br.com.gestaoclean.entity.Cliente;
import br.com.gestaoclean.mapper.ClienteMapper;
import br.com.gestaoclean.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteMapper.toDTO(clienteRepository.findByAtivoTrue());
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return clienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);

        Cliente salvo = clienteRepository.save(cliente);

        return clienteMapper.toDTO(salvo);
    }

    public void excluir(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setAtivo(false);

        clienteRepository.save(cliente);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setObservacoes(dto.getObservacoes());

        Cliente atualizado = clienteRepository.save(cliente);

        return clienteMapper.toDTO(atualizado);
    }
}