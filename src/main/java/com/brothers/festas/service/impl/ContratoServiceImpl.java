package com.brothers.festas.service.impl;

import com.brothers.festas.model.Contrato;
import com.brothers.festas.repository.ContratoRepository;
import com.brothers.festas.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoServiceImpl implements ContratoService {

    private final ContratoRepository contratoRepository;

    @Override
    public Contrato salvarContrato(Contrato contrato) {
        return contratoRepository.save(contrato);
    }

    @Override
    public Page<Contrato> listarContratos(Pageable pageable) {
        return contratoRepository.findAll(pageable);
    }

    @Override
    public Contrato buscarContratoPorId(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado com ID: " + id));
    }

    @Override
    public void deletarContrato(Long id) {
        if (!contratoRepository.existsById(id)) {
            throw new RuntimeException("Contrato não encontrado para deletar com ID: " + id);
        }
        contratoRepository.deleteById(id);
    }
}
