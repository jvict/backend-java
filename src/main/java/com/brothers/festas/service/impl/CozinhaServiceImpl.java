package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.CozinhaRequestDTO;
import com.brothers.festas.dto.response.CozinhaResponseDTO;
import com.brothers.festas.model.Cozinha;
import com.brothers.festas.repository.CozinhaRepository;
import com.brothers.festas.service.CozinhaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CozinhaServiceImpl implements CozinhaService {

    private final CozinhaRepository repository;

    private CozinhaResponseDTO toDTO(Cozinha cozinha) {
        return CozinhaResponseDTO.builder()
                .id(cozinha.getId())
                .descricao(cozinha.getDescricao())
                .build();
    }

    @Override
    public CozinhaResponseDTO salvar(CozinhaRequestDTO dto) {
        Cozinha cozinha = Cozinha.builder()
                .descricao(dto.getDescricao())
                .build();
        return toDTO(repository.save(cozinha));
    }

    @Override
    public List<CozinhaResponseDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CozinhaResponseDTO buscarPorId(Long id) {
        Cozinha cozinha = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cozinha não encontrada"));
        return toDTO(cozinha);
    }

    @Override
    public CozinhaResponseDTO atualizar(Long id, CozinhaRequestDTO dto) {
        Cozinha cozinha = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cozinha não encontrada"));

        cozinha.setDescricao(dto.getDescricao());
        return toDTO(repository.save(cozinha));
    }

    @Override
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cozinha não encontrada");
        }
        repository.deleteById(id);
    }
}
