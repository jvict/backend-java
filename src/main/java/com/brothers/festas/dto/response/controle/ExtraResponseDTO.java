package com.brothers.festas.dto.response.controle;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtraResponseDTO {
    private String personagem;
    private String fotografo;
    private String retrospectiva;
    private String observacoes;

}
