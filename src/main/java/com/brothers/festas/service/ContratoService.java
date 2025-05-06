package com.brothers.festas.service;

import com.brothers.festas.model.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContratoService {
    Contrato salvarContrato(Contrato contrato);
    Page<Contrato> listarContratos(Pageable pageable);
    Contrato buscarContratoPorId(Long id);
    void deletarContrato(Long id);
}
