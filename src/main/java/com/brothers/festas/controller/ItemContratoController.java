package com.brothers.festas.controller;

import com.brothers.festas.dto.request.ItemContratoRequestDTO;
import com.brothers.festas.dto.request.ItemContratoUpdateRequestDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import com.brothers.festas.service.IItemContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item-contrato")
public class ItemContratoController {
    @Autowired
    private IItemContratoService service;

    @PostMapping
    public ResponseEntity<ItemContratoResponseDTO> criar(@Valid @RequestBody ItemContratoRequestDTO request) {
        return ResponseEntity.ok(service.criarItem(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemContratoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ItemContratoResponseDTO>> findAll(Pageable pageable,
                                                                 @RequestParam(name = "descricao", required = false) String descricao) {
        return ResponseEntity.ok().body(service.findAll(pageable, descricao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemContratoResponseDTO> atualizar(@PathVariable Long id,
                                                             @RequestBody ItemContratoUpdateRequestDTO request) {
        ItemContratoResponseDTO atualizado = service.atualizarItem(id, request);
        return ResponseEntity.ok(atualizado);
    }

}
