package com.brothers.festas.dto.request;

import lombok.Data;

@Data
public class AniversarianteRequestDTO {
    private Long id;
    private String nomeAniversariante;
    private Integer idade;
    private Integer idadeNoEvento;
}
