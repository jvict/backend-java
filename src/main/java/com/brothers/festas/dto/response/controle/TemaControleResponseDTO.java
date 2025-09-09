package com.brothers.festas.dto.response.controle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemaControleResponseDTO {
    private Long id;
    private String descricao;
}
