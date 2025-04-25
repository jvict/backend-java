package com.brothers.festas.service;

import com.brothers.festas.dto.request.ClienteRequestDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    ClienteResponseDTO findById(Long Id);

    List<ClienteResponseDTO> findAll();

    ClienteResponseDTO register(ClienteRequestDTO clienteDTO);

    ClienteResponseDTO update( Long id, ClienteRequestDTO clienteDTO);

    ClienteResponseDTO updateStatus(Long id, boolean status);
}
