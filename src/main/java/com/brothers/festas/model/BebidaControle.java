package com.brothers.festas.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class BebidaControle {

    private Long idBebida;
    private String descricao;
    private double litrosChopp;
    private boolean consignado;
}

