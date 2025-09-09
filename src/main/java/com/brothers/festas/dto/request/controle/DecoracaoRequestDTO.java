package com.brothers.festas.dto.request.controle;

import com.brothers.festas.model.enums.EnumPapelaria;
import lombok.Data;

@Data
public class DecoracaoRequestDTO {
    private Long temaId;
    private EnumPapelaria papelaria;
    private String doces;
    private String toalha;
    private boolean arranjoFlor;
    private boolean led;
    private boolean escultura;
    private boolean baloes;
    private String corBaloes;
}
