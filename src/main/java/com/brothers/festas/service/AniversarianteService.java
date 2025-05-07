package com.brothers.festas.service;

import com.brothers.festas.dto.request.AniversarianteRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AniversarianteService {
    AniversarianteResponseDTO criarAniversariante(AniversarianteRequestDTO request);
    AniversarianteResponseDTO findById(Long Id);
    Page<AniversarianteResponseDTO> findByNome(Pageable pageable, String nome);
    Page<AniversarianteResponseDTO> findAll(Pageable pageable);

}
