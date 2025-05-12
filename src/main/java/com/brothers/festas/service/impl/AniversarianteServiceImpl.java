package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.AniversarianteRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.repository.AniversarianteRepository;
import com.brothers.festas.repository.TemaRepository;
import com.brothers.festas.service.IAniversarianteService;
import com.brothers.festas.util.ContratoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AniversarianteServiceImpl implements IAniversarianteService {
    @Autowired
    private AniversarianteRepository aniversarianteRepository;

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private ContratoMapper contratoMapper;

    @Override
    public AniversarianteResponseDTO criarAniversariante(AniversarianteRequestDTO request) {
        Aniversariante aniversariante = new Aniversariante();
        aniversariante.setNome(request.getNome());
        aniversariante.setDataNascimento(request.getDataNascimento());
        aniversariante.setIdade(request.getIdade());
        aniversariante.setIdadeNoEvento(request.getIdadeNoEvento());

        if (request.getTema() != null) {
            temaRepository.findById(request.getTema())
                    .ifPresentOrElse(
                            aniversariante::setTema,
                            () -> { throw new ServiceException("Tema não encontrado com ID: " + request.getTema()); }
                    );
        }

        return new AniversarianteResponseDTO(aniversarianteRepository.save(aniversariante));
    }

    @Override
    public AniversarianteResponseDTO findById(Long id) {
        return contratoMapper.toAniversarianteResponseDTO(returnAniversariante(id));
    }

    @Override
    public Page<AniversarianteResponseDTO> findAllByFilters(Pageable pageable, String nome) {
        String nomeFilter = (nome != null && !nome.isBlank()) ? nome : null;

        return aniversarianteRepository.findAllByFilters(nomeFilter, pageable)
                .map(contratoMapper::toAniversarianteResponseDTO);
    }

    @Override
    public AniversarianteResponseDTO update(Long id, AniversarianteRequestDTO request) {
        Aniversariante aniversariante = returnAniversariante(id);

        contratoMapper.updateAniversarianteData(aniversariante, request);

        return contratoMapper.toAniversarianteResponseDTO(aniversarianteRepository.save(aniversariante));
    }

    private Aniversariante returnAniversariante(Long id) {
        return aniversarianteRepository.findById(id)
                .orElseThrow(()-> new ServiceException("Aniversariante não encontrado no banco de dados!"));
    }
}