package com.brothers.festas.service;

import com.brothers.festas.dto.request.AniversarianteRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;

public interface AniversarianteService {
    AniversarianteResponseDTO criarAniversariante(AniversarianteRequestDTO request);
}
