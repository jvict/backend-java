package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.SobrasRequestDTO;
import com.brothers.festas.dto.response.SobrasResponseDTO;
import com.brothers.festas.model.Sobras;
import com.brothers.festas.repository.SobrasRepository;
import com.brothers.festas.service.SobrasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SobrasServiceImpl implements SobrasService {

    private final SobrasRepository repository;

    private SobrasResponseDTO toResponseDTO(Sobras sobra) {
        return SobrasResponseDTO.builder()
                .id(sobra.getId())
                .descricao(sobra.getDescricao())
                .build();
    }

    @Override
    public SobrasResponseDTO salvar(SobrasRequestDTO dto) {
        Sobras sobra = Sobras.builder()
                .descricao(dto.getDescricao())
                .build();
        return toResponseDTO(repository.save(sobra));
    }

    @Override
    public SobrasResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Sobra não encontrada"));
    }

    @Override
    public List<SobrasResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public SobrasResponseDTO atualizar(Long id, SobrasRequestDTO dto) {
        Sobras sobra = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sobra não encontrada"));

        sobra.setDescricao(dto.getDescricao());
        return toResponseDTO(repository.save(sobra));
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
