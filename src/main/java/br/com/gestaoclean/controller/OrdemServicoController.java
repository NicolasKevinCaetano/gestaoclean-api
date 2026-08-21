package br.com.gestaoclean.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @GetMapping
    public String listar() {
        return "Endpoint de Ordens de Serviço funcionando!";
    }
}
