package com.brothers.festas.controller;

import com.brothers.festas.dto.request.ItemContratoRequestDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import com.brothers.festas.service.ItemContratoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item-contrato")
public class ItemContratoController {
    @Autowired
    private ItemContratoService service;

    @PostMapping
    public ResponseEntity<ItemContratoResponseDTO> criar(@Valid @RequestBody ItemContratoRequestDTO request) {
        return ResponseEntity.ok(service.criarItem(request));
    }
}
