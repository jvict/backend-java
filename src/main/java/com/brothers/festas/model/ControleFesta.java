package com.brothers.festas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_controle_festa")
@Getter
@Setter
@NoArgsConstructor
public class ControleFesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    @Embedded
    private Geral geral;

    @Embedded
    private OficinaControle oficinaControle;

    @Embedded
    private CardapioControle cardapioControle;

    @Embedded
    private BebidaControle bebida;

    @Embedded
    private Decoracao decoracao;

    @Embedded
    private Extra extra;

    //Relacionamento com Contrato
    @OneToOne
    @JoinColumn(name = "contrato_id")
    @ToString.Exclude
    private Contrato contrato;
}
