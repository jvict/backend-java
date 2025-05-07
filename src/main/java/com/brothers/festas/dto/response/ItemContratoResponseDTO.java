package com.brothers.festas.dto.response;

import com.brothers.festas.model.ItemContrato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemContratoResponseDTO {
    private Long id;
    private String descricao;
    private Double valor;

    public ItemContratoResponseDTO(ItemContrato item) {
        this.id = item.getId();
        this.descricao = item.getDescricao();
        this.valor = item.getValor();
    }
}
