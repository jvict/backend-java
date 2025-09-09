package com.brothers.festas.service;

import com.brothers.festas.dto.request.BebidaCadastroRequestDTO;
import com.brothers.festas.dto.response.BebidaResponseDTO;
import com.brothers.festas.model.BebidaCadastro;
import com.brothers.festas.repository.BebidaCadastroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BebidaCadastroService {

    private final BebidaCadastroRepository repository;

    public BebidaResponseDTO salvar(BebidaCadastroRequestDTO dto) {
        BebidaCadastro bebida = BebidaCadastro.builder()
                .descricao(dto.getDescricao())
                .build();
        repository.save(bebida);
        return toResponseDTO(bebida);
    }

    public List<BebidaResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public BebidaResponseDTO buscarPorId(Long id) {
        BebidaCadastro bebida = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bebida não encontrada!"));
        return toResponseDTO(bebida);
    }

    public BebidaResponseDTO atualizar(Long id, BebidaCadastroRequestDTO dto) {
        BebidaCadastro bebida = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bebida não encontrada!"));
        bebida.setDescricao(dto.getDescricao());
        repository.save(bebida);
        return toResponseDTO(bebida);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private BebidaResponseDTO toResponseDTO(BebidaCadastro bebida) {
        return BebidaResponseDTO.builder()
                .idBebida(bebida.getIdBebida())
                .descricao(bebida.getDescricao())
                .build();
    }
}
