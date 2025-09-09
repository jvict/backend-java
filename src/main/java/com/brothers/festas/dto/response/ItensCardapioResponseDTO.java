package com.brothers.festas.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItensCardapioResponseDTO {
    private Long id;
    private Long idCardapio;
    private String descricao;
}
