package br.com.gestaoclean.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSenhaRequestDTO {

    @NotBlank(message = "Nova senha é obrigatória")
    @Size(min = 6, message = "A senha deve possuir no mínimo 6 caracteres")
    private String novaSenha;
}
