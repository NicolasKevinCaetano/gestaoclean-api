package br.com.gestaoclean.controller;

import br.com.gestaoclean.dto.ResumoFinanceiroDTO;
import br.com.gestaoclean.service.FinanceiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/financeiro")
@RequiredArgsConstructor
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    @GetMapping("/resumo")
    public ResponseEntity<ResumoFinanceiroDTO> obterResumo() {

        return ResponseEntity.ok(
                financeiroService.obterResumo()
        );
    }

    @GetMapping("/recebido")
    public ResponseEntity<BigDecimal> obterTotalRecebidoPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate inicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fim) {

        return ResponseEntity.ok(
                financeiroService.obterTotalRecebidoPorPeriodo(inicio, fim)
        );
    }
}

