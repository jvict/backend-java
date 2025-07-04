package com.brothers.festas.service;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ITemaService {

    TemaResponseDTO criarTema(TemaRequestDTO request);
    TemaResponseDTO findById(Long Id);
    Page<TemaResponseDTO> findAllByFilters(Pageable pageable, String descricao);
    String uploadFile(Long temaId, MultipartFile file);
    void deleteFile(String fileUrl);
}
