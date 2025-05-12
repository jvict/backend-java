package com.brothers.festas.service;

import com.brothers.festas.dto.request.ContratoRequestDTO;
import com.brothers.festas.dto.response.ContratoCalendarioResponseDTO;
import com.brothers.festas.dto.response.ContratoResponseDTO;
import com.brothers.festas.model.enums.EnumSituacaoContrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IContratoService {
    ContratoResponseDTO salvarContrato(ContratoRequestDTO contrato);
    Page<ContratoCalendarioResponseDTO> listarContratosCalendario(Pageable pageable);
    ContratoResponseDTO buscarContratoPorId(Long id);
    Page<ContratoResponseDTO> findAllByFilters(Pageable pageable, String nome, LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal);
    ContratoResponseDTO update(Long id, ContratoRequestDTO contratoDTO);
    ContratoResponseDTO updateStatus(Long id, EnumSituacaoContrato situacao);
}
