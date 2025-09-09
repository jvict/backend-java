package com.brothers.festas.dto.request.controle;

import lombok.Data;

@Data
public class ExtraRequestDTO {
    private String personagem;
    private String fotografo;
    private String retrospectiva;
    private String observacoes; // evitei o "ç" para padronizar e evitar problemas
}
