package com.brothers.festas.service.impl;

import com.brothers.festas.dto.response.ContratoCalendarioResponseDTO;
import com.brothers.festas.dto.response.ContratoResponseDTO;
import com.brothers.festas.model.Contrato;
import com.brothers.festas.repository.ContratoRepository;
import com.brothers.festas.service.ContratoService;
import com.brothers.festas.util.ContratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;
    private final ContratoMapper contratoMapper;

    @Override
    public Contrato salvarContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    @Override
    public Page<ContratoResponseDTO> listarContratos(Pageable pageable) {
        return contratoRepository.findAll(pageable)
                .map(contratoMapper::toResponse);
    }

    @Override
    public Page<ContratoCalendarioResponseDTO> listarContratosCalendario(Pageable pageable) {
        return contratoRepository.findAll(pageable)
                .map(contratoMapper::toResponseCalendario);
    }

    @Override
    public ContratoResponseDTO buscarContratoPorId(Long id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado com ID: " + id));

        return contratoMapper.toResponse(contrato);
    }
}
