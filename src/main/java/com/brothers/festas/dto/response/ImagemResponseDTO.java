package com.brothers.festas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagemResponseDTO {

    private Long id;
    private String url;
    private String descricao;
    private String nomeArquivoOriginal;
}
