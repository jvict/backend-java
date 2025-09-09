package com.brothers.festas.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class CardapioControle {

    private Long idCardapio;
    private String descricaoCardapio;
    private String alteracaoCardapio;
    private String adicionais;
    private String bolo;
    private String lanche;
}

