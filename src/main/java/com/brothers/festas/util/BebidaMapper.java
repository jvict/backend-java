package com.brothers.festas.util;

import com.brothers.festas.dto.request.BebidaCadastroRequestDTO;
import com.brothers.festas.dto.response.BebidaResponseDTO;
import com.brothers.festas.model.BebidaControle;
import org.springframework.stereotype.Component;

@Component
public class BebidaMapper {

    // Converte RequestDTO para Entity
    public BebidaControle toEntity(BebidaCadastroRequestDTO dto) {
        if (dto == null) return null;

        BebidaControle bebida = new BebidaControle();
        bebida.setIdBebida(dto.getIdBebida());
        bebida.setLitrosChopp(dto.getLitrosChopp());
        bebida.setConsignado(dto.isConsignado());
        // Os campos nome e descricao não existem no model BebidaControle
        // Se quiser manter, precisa adicionar no model
        return bebida;
    }

    // Converte Entity para ResponseDTO
    public BebidaResponseDTO toResponse(BebidaControle entity) {
        if (entity == null) return null;

        return BebidaResponseDTO.builder()
                .idBebida(entity.getIdBebida())
                .litrosChopp(entity.getLitrosChopp())
                .consignado(entity.isConsignado())
                // Os campos nome e descricao não existem no model, então não podemos setar aqui
                .build();
    }
}
