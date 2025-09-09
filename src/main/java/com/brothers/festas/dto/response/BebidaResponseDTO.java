package com.brothers.festas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BebidaResponseDTO {
    private Long idBebida;
    private String descricao;
    private double litrosChopp;
    private boolean consignado;
}
