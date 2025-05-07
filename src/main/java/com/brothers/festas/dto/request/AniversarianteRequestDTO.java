package com.brothers.festas.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AniversarianteRequestDTO {

    private String nome;
    private String dataNascimento;
    private Integer idade;
    private Integer idadeNoEvento;
    private List<Long> temas;
}
