package com.brothers.festas.service;

import com.brothers.festas.dto.request.ItemContratoRequestDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IItemContratoService {

    ItemContratoResponseDTO criarItem(ItemContratoRequestDTO request);
    ItemContratoResponseDTO findById(Long Id);
    Page<ItemContratoResponseDTO> findAll(Pageable pageable, String descricao);
}
