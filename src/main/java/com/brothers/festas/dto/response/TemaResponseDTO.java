package com.brothers.festas.dto.response;

import com.brothers.festas.model.Tema;
import lombok.Data;

@Data
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
