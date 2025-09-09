package com.brothers.festas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OficinaResponseDTO {
    private Long id;
    private String descricao;
}
