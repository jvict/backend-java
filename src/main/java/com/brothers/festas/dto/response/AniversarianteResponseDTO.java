package com.brothers.festas.dto.response;

import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.model.Tema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<TemaResponseDTO> tema;

    public AniversarianteResponseDTO(Aniversariante aniversariante) {
        this.id = aniversariante.getId();
        this.nome = aniversariante.getNome();
        this.dataNascimento = aniversariante.getDataNascimento();
        this.idade = aniversariante.getIdade();
        this.idadeNoEvento = aniversariante.getIdadeNoEvento();
        this.tema = aniversariante.getTemas().stream()
                .map(TemaResponseDTO::new)
                .collect(Collectors.toList());
    }
}

