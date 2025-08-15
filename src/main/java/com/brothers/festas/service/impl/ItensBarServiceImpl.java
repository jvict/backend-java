package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.ItensBarRequestDTO;
import com.brothers.festas.dto.response.ItensBarResponseDTO;
import com.brothers.festas.model.ItensBar;
import com.brothers.festas.repository.ItensBarRepository;
import com.brothers.festas.service.ItensBarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItensBarServiceImpl implements ItensBarService {

    private final ItensBarRepository repository;

    private ItensBarResponseDTO toResponseDTO(ItensBar item) {
        return ItensBarResponseDTO.builder()
                .id(item.getId())
                .descricao(item.getDescricao())
                .build();
    }

    @Override
    public ItensBarResponseDTO salvar(ItensBarRequestDTO dto) {
        ItensBar item = ItensBar.builder()
                .descricao(dto.getDescricao())
                .build();
        return toResponseDTO(repository.save(item));
    }

    @Override
    public ItensBarResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Item de Bar não encontrado"));
    }

    @Override
    public List<ItensBarResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public ItensBarResponseDTO atualizar(Long id, ItensBarRequestDTO dto) {
        ItensBar item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de Bar não encontrado"));

        item.setDescricao(dto.getDescricao());
        return toResponseDTO(repository.save(item));
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
