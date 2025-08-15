package com.brothers.festas.service;

import com.brothers.festas.dto.request.SobrasRequestDTO;
import com.brothers.festas.dto.response.SobrasResponseDTO;

import java.util.List;

public interface SobrasService {
    SobrasResponseDTO salvar(SobrasRequestDTO dto);
    SobrasResponseDTO buscarPorId(Long id);
    List<SobrasResponseDTO> listarTodos();
    SobrasResponseDTO atualizar(Long id, SobrasRequestDTO dto);
    void deletar(Long id);
}
