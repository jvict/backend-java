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
    private String nome;
    private String dataNascimento;
    private Integer idade;
    private Integer idadeNoEvento;
    private TemaResponseDTO tema;

    public AniversarianteResponseDTO(Aniversariante aniversariante) {
        this.id = aniversariante.getId();
        this.nome = aniversariante.getNome();
        this.dataNascimento = aniversariante.getDataNascimento();
        this.idade = aniversariante.getIdade();
        this.idadeNoEvento = aniversariante.getIdadeNoEvento();
        this.tema = aniversariante.getTema() != null ? new TemaResponseDTO(aniversariante.getTema()) : null;
    }
}

