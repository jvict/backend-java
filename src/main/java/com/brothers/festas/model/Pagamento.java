package com.brothers.festas.model;

import com.brothers.festas.model.enums.EnumMeioPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pagamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "meio_pagamento")
    private EnumMeioPagamento meioPagamento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    private Boolean recebido;

    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;
}

