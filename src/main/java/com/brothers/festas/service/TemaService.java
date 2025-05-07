package com.brothers.festas.service;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemaService {

    TemaResponseDTO criarTema(TemaRequestDTO request);
    TemaResponseDTO findById(Long Id);
    Page<TemaResponseDTO> findByDescricao(Pageable pageable, String nome);
    Page<TemaResponseDTO> findAll(Pageable pageable);
}
