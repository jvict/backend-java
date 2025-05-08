package com.brothers.festas.dto.request;

import lombok.Data;

@Data
public class AniversarianteRequestDTO {

    private String nome;
    private String dataNascimento;
    private Integer idade;
    private Integer idadeNoEvento;
    private Long tema;
}
