package com.brothers.festas.dto.request.controle;

import lombok.Data;

@Data
public class CardapioControleRequestDTO {
    private Long idCardapio;
    private String alteracaoCardapio;
    private String adicionais;
    private String bolo;
    private String lanche;
}
