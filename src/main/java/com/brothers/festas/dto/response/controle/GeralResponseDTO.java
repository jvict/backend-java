package com.brothers.festas.dto.response.controle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeralResponseDTO {
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
