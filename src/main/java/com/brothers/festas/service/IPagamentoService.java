package com.brothers.festas.service;

import com.brothers.festas.dto.request.PagamentoRequestDTO;
import com.brothers.festas.dto.response.PagamentoResponseDTO;
import com.brothers.festas.exception.ServiceException;

import java.util.List;

public interface IPagamentoService {

    List<PagamentoResponseDTO> criarPagamento(Long idContrato, List<PagamentoRequestDTO> request) throws ServiceException;
}
