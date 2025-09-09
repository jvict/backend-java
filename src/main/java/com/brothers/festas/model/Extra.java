package com.brothers.festas.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Extra {

    private String personagem;
    private String fotografo;
    private String retrospectiva;
    private String observacoes;
}

