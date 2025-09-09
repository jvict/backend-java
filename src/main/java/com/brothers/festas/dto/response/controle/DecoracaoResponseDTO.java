
package com.brothers.festas.dto.response.controle;

import com.brothers.festas.model.enums.EnumPapelaria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DecoracaoResponseDTO {
    private Long temaId;
    private String temaDescricao;
    private EnumPapelaria papelaria;
    private String doces;
    private String toalha;
    private boolean arranjoFlor;
    private boolean led;
    private boolean escultura;
    private boolean baloes;
    private String corBaloes;
}
