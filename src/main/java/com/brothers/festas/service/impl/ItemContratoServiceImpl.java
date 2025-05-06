package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.ItemContratoRequestDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import com.brothers.festas.model.ItemContrato;
import com.brothers.festas.repository.ItemContratoRepository;
import com.brothers.festas.service.ItemContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemContratoServiceImpl implements ItemContratoService {
    @Autowired
    private ItemContratoRepository itemContratoRepository;

    @Override
    public ItemContratoResponseDTO criarItem(ItemContratoRequestDTO request) {
        ItemContrato item = new ItemContrato();
        item.setDescricao(request.getDescricao());
        item.setValor(request.getValor());

        return new ItemContratoResponseDTO(itemContratoRepository.save(item));
    }
}