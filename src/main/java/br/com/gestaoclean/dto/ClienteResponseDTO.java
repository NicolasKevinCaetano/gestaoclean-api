package br.com.gestaoclean.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String observacoes;
    private Boolean ativo;
    private LocalDateTime dataCadastro;

    public ClienteResponseDTO(Long id, String nome, String telefone, String email,
                              String cpf, String observacoes, Boolean ativo,
                              LocalDateTime dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.observacoes = observacoes;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
    }
}