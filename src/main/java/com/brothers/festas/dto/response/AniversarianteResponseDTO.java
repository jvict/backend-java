package com.brothers.festas.dto.response;

import com.brothers.festas.model.Aniversariante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AniversarianteResponseDTO {
    private Long id;
    private String nomeAniversariante;
    private Integer idade;
    private Integer idadeNoEvento;

    public AniversarianteResponseDTO(Aniversariante aniversariante) {
        this.id = aniversariante.getId();
        this.nomeAniversariante = aniversariante.getNomeAniversariante();
        this.idade = aniversariante.getIdade();
        this.idadeNoEvento = aniversariante.getIdadeNoEvento();
    }
}

