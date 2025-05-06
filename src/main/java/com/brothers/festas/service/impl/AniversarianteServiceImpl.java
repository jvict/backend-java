package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.AniversarianteRequestDTO;
import com.brothers.festas.dto.request.ItemContratoRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.model.ItemContrato;
import com.brothers.festas.repository.AniversarianteRepository;
import com.brothers.festas.repository.ItemContratoRepository;
import com.brothers.festas.service.AniversarianteService;
import com.brothers.festas.service.ItemContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AniversarianteServiceImpl implements AniversarianteService {
    @Autowired
    private AniversarianteRepository aniversarianteRepository;

    @Override
    public AniversarianteResponseDTO criarAniversariante(AniversarianteRequestDTO request) {
        Aniversariante aniversariante = new Aniversariante();
        aniversariante.setNome(request.getNome());
        aniversariante.setDataNascimento(request.getDataNascimento());
        aniversariante.setIdade(request.getIdade());
        aniversariante.setIdadeNoEvento(request.getIdadeNoEvento());

        return new AniversarianteResponseDTO(aniversarianteRepository.save(aniversariante));
    }
}