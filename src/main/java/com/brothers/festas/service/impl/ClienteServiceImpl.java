package com.brothers.festas.service.impl;

import com.brothers.festas.dto.request.ClienteRequestDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Cliente;
import com.brothers.festas.repository.ClienteRepository;
import com.brothers.festas.service.IClienteService;
import com.brothers.festas.util.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteResponseDTO findById(Long id) {
        return clienteMapper.toClienteDTO(returnCliente(id));
    }

    @Override
    public Page<ClienteResponseDTO> findByNome(Pageable pageable, String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome, pageable)
                .map(clienteMapper::toClienteDTO);
    }

    @Override
    public Page<ClienteResponseDTO> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(clienteMapper::toClienteDTO);
    }

    @Override
    public ClienteResponseDTO register(ClienteRequestDTO clienteDTO) throws ServiceException{
        Optional<Cliente> byDocumento = clienteRepository.findByDocumento(clienteDTO.getDocumento());
        if (byDocumento.isPresent()){
            throw new ServiceException("Cliente com esse documento já existe na base de dados!");
        }

        Cliente cliente = clienteMapper.toCliente(clienteDTO);

        return clienteMapper.toClienteDTO(clienteRepository.save(cliente));
    }

    @Override
    public ClienteResponseDTO update(Long id, ClienteRequestDTO clienteDTO) {
        Cliente cliente = returnCliente(id);

        clienteMapper.updateClienteData(cliente, clienteDTO);

        return clienteMapper.toClienteDTO(clienteRepository.save(cliente));
    }

    @Override
    public ClienteResponseDTO updateStatus(Long id, boolean status) {
        Cliente cliente = returnCliente(id);

        cliente.setStatus(status);
        cliente.setDataAtualizacao(LocalDateTime.now());

        return clienteMapper.toClienteDTO(clienteRepository.save(cliente));
    }


    private Cliente returnCliente(Long id) {
       return clienteRepository.findById(id)
               .orElseThrow(()-> new ServiceException("Usuario não encontrado no banco de dados!"));
    }


}
