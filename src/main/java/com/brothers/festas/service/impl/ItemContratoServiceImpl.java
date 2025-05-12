package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.ItemContratoRequestDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.ItemContrato;
import com.brothers.festas.repository.ItemContratoRepository;
import com.brothers.festas.service.IItemContratoService;
import com.brothers.festas.util.ContratoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemContratoServiceImpl implements IItemContratoService {
    @Autowired
    private ItemContratoRepository itemContratoRepository;

    @Autowired
    private ContratoMapper contratoMapper;

    @Override
    public ItemContratoResponseDTO criarItem(ItemContratoRequestDTO request) {
        ItemContrato item = new ItemContrato();
        item.setDescricao(request.getDescricao());
        item.setValor(request.getValor());

        return new ItemContratoResponseDTO(itemContratoRepository.save(item));
    }

    @Override
    public ItemContratoResponseDTO findById(Long id) {
        return contratoMapper.toItemContratoResponseDTO(returnItemContrato(id));
    }


    @Override
    public Page<ItemContratoResponseDTO> findAll(Pageable pageable, String descricao) {
        return itemContratoRepository.findAllByFilters(pageable, descricao)
                .map(contratoMapper::toItemContratoResponseDTO);
    }

    private ItemContrato returnItemContrato(Long id) {
        return itemContratoRepository.findById(id)
                .orElseThrow(()-> new ServiceException("Item do Contrato não encontrado no banco de dados!"));
    }
}