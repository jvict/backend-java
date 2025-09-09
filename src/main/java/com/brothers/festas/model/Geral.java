package com.brothers.festas.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Geral {

    private boolean autorizadoPostarFotos;
    private String instagram;
    private String responsavel;
    private String telefone;
    private String aniversariante;
    private int idadeAniversariante;
    private int numeroAdultos;
    private int criancas1a3;
    private int criancas4a7;
    private int criancas8a12;
    private int adultosPresentes;
    private int criancasPresentes;
}
