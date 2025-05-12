package com.brothers.festas.service;

import com.brothers.festas.dto.request.PagamentoRequestDTO;
import com.brothers.festas.dto.response.PagamentoResponseDTO;
import com.brothers.festas.exception.ServiceException;

public interface IPagamentoService {

    PagamentoResponseDTO criarPagamento(Long idContrato, PagamentoRequestDTO request) throws ServiceException;
}
