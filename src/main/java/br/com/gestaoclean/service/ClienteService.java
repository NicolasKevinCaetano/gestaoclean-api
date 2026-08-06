package br.com.gestaoclean.service;

import br.com.gestaoclean.dto.ClienteResponseDTO;
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
        return clienteMapper.toDTO(clienteRepository.findAll());
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return clienteMapper.toDTO(cliente);
    }

    public ClienteResponseDTO salvar(Cliente cliente) {
        Cliente salvo = clienteRepository.save(cliente);
        return clienteMapper.toDTO(salvo);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}