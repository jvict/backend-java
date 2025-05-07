package com.brothers.festas.util;

import com.brothers.festas.dto.request.ClienteRequestDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;
import com.brothers.festas.model.Cliente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {


    public Cliente toCliente(ClienteRequestDTO clienteDTO) {
        return Cliente.builder()
                .nome(clienteDTO.getNome())
                .celular(clienteDTO.getCelular())
                .email(clienteDTO.getEmail())
                .documento(clienteDTO.getDocumento())
                .dataCadastro(LocalDateTime.now())
                .cep(clienteDTO.getCep())
                .endereco(clienteDTO.getEndereco())
                .numero(clienteDTO.getNumero())
                .complemento(clienteDTO.getComplemento())
                .bairro(clienteDTO.getBairro())
                .cidade(clienteDTO.getCidade())
                .uf(clienteDTO.getUf())
                .status(true)

                .build();
    }

    public ClienteResponseDTO toClienteDTO(Cliente cliente){
            return new ClienteResponseDTO(cliente);

    }

    public List<ClienteResponseDTO> toClienteDTO(List<Cliente>clienteList) {
        return clienteList.stream().map(ClienteResponseDTO::new).collect(Collectors.toList());

    }

    public void updateClienteData(Cliente cliente, ClienteRequestDTO clienteRequestDTO){
        if (clienteRequestDTO.getNome() != null) {
            cliente.setNome(clienteRequestDTO.getNome());
        }
        if (clienteRequestDTO.getCelular() != null) {
            cliente.setCelular(clienteRequestDTO.getCelular());
        }
        if (clienteRequestDTO.getEmail() != null) {
            cliente.setEmail(clienteRequestDTO.getEmail());
        }
        if (clienteRequestDTO.getCep() != null) {
            cliente.setCep(clienteRequestDTO.getCep());
        }
        if (clienteRequestDTO.getEndereco() != null) {
            cliente.setEndereco(clienteRequestDTO.getEndereco());
        }
        if (clienteRequestDTO.getNumero() != null) {
            cliente.setNumero(clienteRequestDTO.getNumero());
        }
        if (clienteRequestDTO.getComplemento() != null) {
            cliente.setComplemento(clienteRequestDTO.getComplemento());
        }
        if (clienteRequestDTO.getBairro() != null) {
            cliente.setBairro(clienteRequestDTO.getBairro());
        }
        if (clienteRequestDTO.getCidade() != null) {
            cliente.setCidade(clienteRequestDTO.getCidade());
        }
        if (clienteRequestDTO.getUf() != null) {
            cliente.setUf(clienteRequestDTO.getUf());
        }
        if (clienteRequestDTO.getStatus() != null) {
            cliente.setStatus(clienteRequestDTO.getStatus());
        }
        cliente.setDataAtualizacao(LocalDateTime.now());
    }

    public ClienteResponseDTO toClienteDTO(Optional<Cliente> cliente) {
        return new ClienteResponseDTO(cliente.get());
    }
}
