package com.brothers.festas.service;

import com.brothers.festas.dto.request.ItemContratoRequestDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;

public interface ItemContratoService {

    ItemContratoResponseDTO criarItem(ItemContratoRequestDTO request);
}
