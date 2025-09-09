package com.brothers.festas.service;

import com.brothers.festas.dto.request.controle.ControleFestaRequestDTO;
import com.brothers.festas.dto.response.controle.ControleFestaResponseDTO;
import com.brothers.festas.model.ControleFesta;
import com.brothers.festas.repository.ControleFestaRepository;
import com.brothers.festas.util.ControleFestaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ControleFestaService {

    private final ControleFestaRepository controleFestaRepository;
    private final ControleFestaMapper controleFestaMapper;

    public ControleFestaResponseDTO createControleFesta(ControleFestaRequestDTO dto) {
        // Converte o DTO para entity usando o mapper
        ControleFesta festa = controleFestaMapper.toEntity(dto);

        // Salva a entity
        festa = controleFestaRepository.save(festa);

        // Converte a entity salva para ResponseDTO
        return controleFestaMapper.toResponse(festa);
    }

    public ControleFestaResponseDTO updateControleFesta(Long id, ControleFestaRequestDTO dto) {
        ControleFesta festa = controleFestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ControleFesta não encontrado"));

        // Atualiza os campos da entity usando o mapper
        if (dto.getGeral() != null) {
            festa.setGeral(controleFestaMapper.toGeralEntity(dto.getGeral()));
        }
        if (dto.getOficinaId() != null) {
            festa.setOficinaControle(controleFestaMapper.toOficinaEntity(dto.getOficinaId()));
        }
        if (dto.getCardapio() != null) {
            festa.setCardapioControle(controleFestaMapper.toCardapioEntity(dto.getCardapio()));
        }
        if (dto.getBebida() != null) {
            festa.setBebida(controleFestaMapper.toBebidaEntity(dto.getBebida()));
        }
        if (dto.getDecoracao() != null) {
            festa.setDecoracao(controleFestaMapper.toDecoracaoEntity(dto.getDecoracao()));
        }
        if (dto.getExtra() != null) {
            festa.setExtra(controleFestaMapper.toExtraEntity(dto.getExtra()));
        }

        festa.setData(dto.getData());
        festa.setHorarioInicio(dto.getHorarioInicio());
        festa.setHorarioFim(dto.getHorarioFim());

        // Salva a entity atualizada
        festa = controleFestaRepository.save(festa);

        return controleFestaMapper.toResponse(festa);
    }

    public List<ControleFestaResponseDTO> getAll() {
        return controleFestaRepository.findAll().stream()
                .map(controleFestaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ControleFestaResponseDTO getById(Long id) {
        ControleFesta festa = controleFestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ControleFesta não encontrado"));

        return controleFestaMapper.toResponse(festa);
    }

    public void delete(Long id) {
        controleFestaRepository.deleteById(id);
    }
}
