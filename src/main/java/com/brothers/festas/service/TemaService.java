package com.brothers.festas.service;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;

public interface TemaService {

    TemaResponseDTO criarTema(TemaRequestDTO request);
}
