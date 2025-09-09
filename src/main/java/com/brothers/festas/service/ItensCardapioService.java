package com.brothers.festas.service;

import com.brothers.festas.dto.request.ItensCardapioRequestDTO;
import com.brothers.festas.dto.response.ItensCardapioResponseDTO;
import com.brothers.festas.model.ItensCardapio;
import com.brothers.festas.repository.ItensCardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItensCardapioService {

    private final ItensCardapioRepository repository;

    public List<ItensCardapioResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ItensCardapioResponseDTO criar(ItensCardapioRequestDTO dto) {
        ItensCardapio item = new ItensCardapio();
        item.setIdCardapio(dto.getIdCardapio());
        item.setDescricao(dto.getDescricao());

        ItensCardapio salvo = repository.save(item);
        return toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private ItensCardapioResponseDTO toResponseDTO(ItensCardapio item) {
        return ItensCardapioResponseDTO.builder()
                .id(item.getId())
                .idCardapio(item.getIdCardapio())
                .descricao(item.getDescricao())
                .build();
    }
}
