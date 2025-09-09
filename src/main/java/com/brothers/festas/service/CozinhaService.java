package com.brothers.festas.service;

import com.brothers.festas.dto.request.CozinhaRequestDTO;
import com.brothers.festas.dto.response.CozinhaResponseDTO;

import java.util.List;

public interface CozinhaService {
    CozinhaResponseDTO salvar(CozinhaRequestDTO dto);
    List<CozinhaResponseDTO> listar();
    CozinhaResponseDTO buscarPorId(Long id);
    CozinhaResponseDTO atualizar(Long id, CozinhaRequestDTO dto);
    void deletar(Long id);
}
