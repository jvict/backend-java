package com.brothers.festas.controller;

import com.brothers.festas.dto.request.ClienteRequestDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.service.IClienteImportService;
import com.brothers.festas.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
@CrossOrigin(origins = "http:localhost:3000/")
@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;
    private final IClienteImportService clienteImportService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> findAll(@RequestParam(name = "nome", required = false) String nome,
                                                            Pageable pageable) {
        return ResponseEntity.ok().body(clienteService.findAllByFilters(nome, pageable));
    }

    @PostMapping("/importar")
    public ResponseEntity<String> importar(@RequestParam("arquivo") MultipartFile arquivo) {
        try {
            clienteImportService.importarExcel(arquivo.getInputStream());
            return ResponseEntity.ok("Importação realizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na importação: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody ClienteRequestDTO clienteDTO, UriComponentsBuilder uriBuilder) throws ServiceException {
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

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(@RequestBody ClienteRequestDTO clienteDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.update(id, clienteDTO));
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable String status) {
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

