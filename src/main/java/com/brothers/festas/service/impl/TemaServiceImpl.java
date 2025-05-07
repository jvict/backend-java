package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Tema;
import com.brothers.festas.repository.TemaRepository;
import com.brothers.festas.service.TemaService;
import com.brothers.festas.util.ContratoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemaServiceImpl implements TemaService {
    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private ContratoMapper contratoMapper;

    public TemaResponseDTO criarTema(TemaRequestDTO request) {
        Tema tema = new Tema();
        tema.setDescricao(request.getDescricao());
        tema.setObservacoes(request.getObservacoes());

        return new TemaResponseDTO(temaRepository.save(tema));
    }

    @Override
    public TemaResponseDTO findById(Long id) {
        return contratoMapper.toTemaResponseDTO(returnTema(id));
    }

    @Override
    public Page<TemaResponseDTO> findByDescricao(Pageable pageable, String descricao) {
        return temaRepository.findByDescricaoContainingIgnoreCase(descricao, pageable)
                .map(contratoMapper::toTemaResponseDTO);
    }

    @Override
    public Page<TemaResponseDTO> findAll(Pageable pageable) {
        return temaRepository.findAll(pageable)
                .map(contratoMapper::toTemaResponseDTO);
    }

    private Tema returnTema(Long id) {
        return temaRepository.findById(id)
                .orElseThrow(()-> new ServiceException("Tema não encontrado no banco de dados!"));
    }
}