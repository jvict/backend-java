package com.brothers.festas.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SobrasResponseDTO {
    private Long id;
    private String descricao;
}
