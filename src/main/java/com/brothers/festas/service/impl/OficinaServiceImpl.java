package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.OficinaRequestDTO;
import com.brothers.festas.dto.response.OficinaResponseDTO;
import com.brothers.festas.model.Oficina;
import com.brothers.festas.repository.OficinaRepository;
import com.brothers.festas.service.OficinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OficinaServiceImpl implements OficinaService {

    private final OficinaRepository repository;

    private OficinaResponseDTO toResponseDTO(Oficina oficina) {
        return OficinaResponseDTO.builder()
                .id(oficina.getId())
                .descricao(oficina.getDescricao())
                .build();
    }

    @Override
    public OficinaResponseDTO salvar(OficinaRequestDTO dto) {
        Oficina oficina = Oficina.builder()
                .descricao(dto.getDescricao())
                .build();
        return toResponseDTO(repository.save(oficina));
    }

    @Override
    public OficinaResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Oficina não encontrada"));
    }

    @Override
    public List<OficinaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public OficinaResponseDTO atualizar(Long id, OficinaRequestDTO dto) {
        Oficina oficina = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oficina não encontrada"));

        oficina.setDescricao(dto.getDescricao());
        return toResponseDTO(repository.save(oficina));
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
