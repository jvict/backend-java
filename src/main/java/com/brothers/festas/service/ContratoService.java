package com.brothers.festas.service;

import com.brothers.festas.dto.response.ContratoCalendarioResponseDTO;
import com.brothers.festas.dto.response.ContratoResponseDTO;
import com.brothers.festas.model.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContratoService {
    Contrato salvarContrato(Contrato contrato);
    Page<ContratoResponseDTO> listarContratos(Pageable pageable);
    Page<ContratoCalendarioResponseDTO> listarContratosCalendario(Pageable pageable);
    ContratoResponseDTO buscarContratoPorId(Long id);
}
