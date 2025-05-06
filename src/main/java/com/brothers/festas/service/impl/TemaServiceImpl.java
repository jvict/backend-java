package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.model.Tema;
import com.brothers.festas.repository.TemaRepository;
import com.brothers.festas.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaServiceImpl implements TemaService {
    @Autowired
    private TemaRepository temaRepository;

    public TemaResponseDTO criarTema(TemaRequestDTO request) {
        Tema tema = new Tema();
        tema.setDescricao(request.getDescricao());
        tema.setObservacoes(request.getObservacoes());

        return new TemaResponseDTO(temaRepository.save(tema));
    }
}