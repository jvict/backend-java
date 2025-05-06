package com.brothers.festas.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TemaRequestDTO {

    @NotBlank
    private String descricao;

    private String observacoes;
}
