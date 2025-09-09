package com.brothers.festas.dto.response.controle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardapioControleResponseDTO {
    private Long idCardapio;
    private String descricaoCardapio;
    private String alteracaoCardapio;
    private String adicionais;
    private String bolo;
    private String lanche;
}

