package com.brothers.festas.service;

import com.brothers.festas.dto.request.ClienteRequestDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteService {
    ClienteResponseDTO findById(Long Id);
    Page<ClienteResponseDTO> findAllByFilters(String nome, Pageable pageable);
    ClienteResponseDTO register(ClienteRequestDTO clienteDTO);
    ClienteResponseDTO update(Long id, ClienteRequestDTO clienteDTO);
    ClienteResponseDTO updateStatus(Long id, boolean status);
}
