package com.brothers.festas.service;

import com.brothers.festas.dto.request.ItemContratoRequestDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemContratoService {

    ItemContratoResponseDTO criarItem(ItemContratoRequestDTO request);
    ItemContratoResponseDTO findById(Long Id);
    Page<ItemContratoResponseDTO> findByDescricao(Pageable pageable, String nome);
    Page<ItemContratoResponseDTO> findAll(Pageable pageable);
}
