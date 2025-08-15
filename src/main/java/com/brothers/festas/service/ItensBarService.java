package com.brothers.festas.service;

import com.brothers.festas.dto.request.ItensBarRequestDTO;
import com.brothers.festas.dto.response.ItensBarResponseDTO;

import java.util.List;

public interface ItensBarService {
    ItensBarResponseDTO salvar(ItensBarRequestDTO dto);
    ItensBarResponseDTO buscarPorId(Long id);
    List<ItensBarResponseDTO> listarTodos();
    ItensBarResponseDTO atualizar(Long id, ItensBarRequestDTO dto);
    void deletar(Long id);
}
