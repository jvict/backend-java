package com.brothers.festas.controller;

import com.brothers.festas.dto.request.ClienteRequestDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Usuario;
import com.brothers.festas.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> register (@RequestBody ClienteRequestDTO clienteDTO, UriComponentsBuilder uriBuilder) throws ServiceException {

        try {
            ClienteResponseDTO clienteResponseDTO = clienteService.register(clienteDTO);
            URI uri = uriBuilder.path("/cliente/id").buildAndExpand(clienteResponseDTO.getId()).toUri();

            return ResponseEntity.created(uri).body(clienteResponseDTO);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@RequestBody ClienteRequestDTO clienteDTO, @PathVariable(name = "id")Long id) {
        return ResponseEntity.ok().body(clienteService.update(id, clienteDTO));
    }

    @PutMapping(value = "/{id}/status/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable(name = "id")Long id, @PathVariable(name = "status")String status) {
        try {
            boolean novoStatus = Boolean.parseBoolean(status);
            return ResponseEntity.ok().body(clienteService.updateStatus(id, novoStatus));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor de status inválido. Use 'true' ou 'false'.");
        }

    }
}
