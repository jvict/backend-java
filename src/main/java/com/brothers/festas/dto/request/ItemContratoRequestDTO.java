package com.brothers.festas.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemContratoRequestDTO {

    @NotBlank
    private String descricao;

    @NotNull
    private Double valor;
}
