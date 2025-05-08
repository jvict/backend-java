package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.AniversarianteRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.model.Cliente;
import com.brothers.festas.model.Tema;
import com.brothers.festas.repository.AniversarianteRepository;
import com.brothers.festas.repository.TemaRepository;
import com.brothers.festas.service.AniversarianteService;
import com.brothers.festas.util.ContratoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AniversarianteServiceImpl implements AniversarianteService {
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
    public Page<AniversarianteResponseDTO> findByNome(Pageable pageable, String nome) {
        return aniversarianteRepository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(contratoMapper::toAniversarianteResponseDTO);
    }

    @Override
    public Page<AniversarianteResponseDTO> findAll(Pageable pageable) {
        return aniversarianteRepository.findAll(pageable)
                .map(contratoMapper::toAniversarianteResponseDTO);
    }

    private Aniversariante returnAniversariante(Long id) {
        return aniversarianteRepository.findById(id)
                .orElseThrow(()-> new ServiceException("Aniversariante não encontrado no banco de dados!"));
    }
}