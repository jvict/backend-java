package com.brothers.festas.service;

import com.brothers.festas.dto.request.CardapioRequestDTO;
import com.brothers.festas.dto.response.CardapioResponseDTO;

import java.util.List;

public interface CardapioService {
    CardapioResponseDTO salvar(CardapioRequestDTO dto);
    CardapioResponseDTO buscarPorId(Long id);
    List<CardapioResponseDTO> listarTodos();
    CardapioResponseDTO atualizar(Long id, CardapioRequestDTO dto);
    void deletar(Long id);
}
