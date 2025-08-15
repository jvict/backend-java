package com.brothers.festas.service;

import com.brothers.festas.dto.request.OficinaRequestDTO;
import com.brothers.festas.dto.response.OficinaResponseDTO;

import java.util.List;

public interface OficinaService {
    OficinaResponseDTO salvar(OficinaRequestDTO dto);
    OficinaResponseDTO buscarPorId(Long id);
    List<OficinaResponseDTO> listarTodos();
    OficinaResponseDTO atualizar(Long id, OficinaRequestDTO dto);
    void deletar(Long id);
}
