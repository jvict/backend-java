package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.CardapioRequestDTO;
import com.brothers.festas.dto.response.CardapioResponseDTO;
import com.brothers.festas.model.Cardapio;
import com.brothers.festas.repository.CardapioRepository;
import com.brothers.festas.service.CardapioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardapioServiceImpl implements CardapioService {

    private final CardapioRepository repository;

    private CardapioResponseDTO toResponseDTO(Cardapio cardapio) {
        return CardapioResponseDTO.builder()
                .id(cardapio.getId())
                .descricao(cardapio.getDescricao())
                .build();
    }

    @Override
    public CardapioResponseDTO salvar(CardapioRequestDTO dto) {
        Cardapio cardapio = Cardapio.builder()
                .descricao(dto.getDescricao())
                .build();
        return toResponseDTO(repository.save(cardapio));
    }

    @Override
    public CardapioResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado"));
    }

    @Override
    public List<CardapioResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CardapioResponseDTO atualizar(Long id, CardapioRequestDTO dto) {
        Cardapio cardapio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cardápio não encontrado"));

        cardapio.setDescricao(dto.getDescricao());
        return toResponseDTO(repository.save(cardapio));
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
