package com.brothers.festas.dto.response;

import com.brothers.festas.model.Tema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemaResponseDTO {
    private Long id;
    private String descricao;
    private String observacoes;

    public TemaResponseDTO(Tema tema) {
        this.id = tema.getId();
        this.descricao = tema.getDescricao();
        this.observacoes = tema.getObservacoes();
    }
}
