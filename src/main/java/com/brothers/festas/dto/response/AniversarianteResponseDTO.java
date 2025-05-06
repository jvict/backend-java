package com.brothers.festas.dto.response;

import com.brothers.festas.model.Aniversariante;
import lombok.Data;

@Data
public class AniversarianteResponseDTO {
    private Long id;
    private String nome;
    private String dataNascimento;
    private Integer idade;
    private Integer idadeNoEvento;

    public AniversarianteResponseDTO(Aniversariante aniversariante) {
        this.id = aniversariante.getId();
        this.nome = aniversariante.getNome();
        this.dataNascimento = aniversariante.getDataNascimento();
        this.idade = aniversariante.getIdade();
        this.idadeNoEvento = aniversariante.getIdadeNoEvento();
    }
}

