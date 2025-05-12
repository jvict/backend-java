package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Tema;
import com.brothers.festas.repository.TemaRepository;
import com.brothers.festas.service.ITemaService;
import com.brothers.festas.util.ContratoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TemaServiceImpl implements ITemaService {
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
    public Page<TemaResponseDTO> findAllByFilters(Pageable pageable, String descricao) {
        return temaRepository.findAllByFilters(pageable, descricao)
                .map(contratoMapper::toTemaResponseDTO);
    }

    private Tema returnTema(Long id) {
        return temaRepository.findById(id)
                .orElseThrow(()-> new ServiceException("Tema não encontrado no banco de dados!"));
    }
}