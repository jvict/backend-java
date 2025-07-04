package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.ContratoRequestDTO;
import com.brothers.festas.dto.response.ContratoCalendarioResponseDTO;
import com.brothers.festas.dto.response.ContratoResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Cliente;
import com.brothers.festas.model.Contrato;
import com.brothers.festas.model.enums.EnumSituacaoContrato;
import com.brothers.festas.repository.ContratoRepository;
import com.brothers.festas.service.IContratoService;
import com.brothers.festas.util.ContratoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.brothers.festas.repository.ContratoSpecifications.byFilters;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements IContratoService {

    private final ContratoRepository contratoRepository;
    private final ContratoMapper contratoMapper;

    @Override
    public ContratoResponseDTO salvarContrato(ContratoRequestDTO contratoRequestDTO) {
        Contrato contrato = contratoMapper.toEntity(contratoRequestDTO);
        contrato.setDataCadastro(LocalDateTime.now());

        return contratoMapper.toResponse(contratoRepository.save(contrato));
    }

    @Override
    public Page<ContratoResponseDTO> findAllByFilters(Pageable pageable, String nome, LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal) {
        Page<Contrato> page = contratoRepository.findAll(byFilters(nome, dataHoraInicial, dataHoraFinal), pageable);
        return page.map(contratoMapper::toResponse);
    }

    @Override
    public ContratoResponseDTO update(Long id, ContratoRequestDTO contratoDTO) {
        Contrato contrato = returnContrato(id);

        contratoMapper.updateContratoData(contrato, contratoDTO);
        contrato.setDataAtualizacao(LocalDateTime.now());

        return contratoMapper.toResponse(contratoRepository.save(contrato));
    }

    @Override
    public ContratoResponseDTO updateStatus(Long id, EnumSituacaoContrato situacao) {
        Contrato contrato = returnContrato(id);

        contrato.setSituacao(situacao);
        contrato.setDataAtualizacao(LocalDateTime.now());

        return contratoMapper.toResponse(contratoRepository.save(contrato));
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

    private Contrato returnContrato(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(()-> new ServiceException("Contrato não encontrado no banco de dados!"));
    }
}
