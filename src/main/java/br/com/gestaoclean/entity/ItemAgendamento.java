package br.com.gestaoclean.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemAgendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agendamento_id", nullable = false)
    private Agendamento agendamento;

    @Column(nullable = false, length = 100)
    private String servico;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
}