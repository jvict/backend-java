package com.brothers.festas.model;

import com.brothers.festas.model.enums.EnumPapelaria;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Decoracao {

    private Long temaId;
    private String temaDescricao;

    @Enumerated(EnumType.STRING)
    private EnumPapelaria papelaria;

    private String doces;
    private String toalha;
    private boolean arranjoFlor;
    private boolean led;
    private boolean escultura;
    private boolean baloes;
    private String corBaloes;

    public void setPapelaria(EnumPapelaria papelaria) {
    }
}

