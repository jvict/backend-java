package com.brothers.festas.controller;

import com.brothers.festas.dto.request.controle.ControleFestaRequestDTO;
import com.brothers.festas.dto.response.controle.ControleFestaResponseDTO;
import com.brothers.festas.service.ControleFestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controle-festa")
@RequiredArgsConstructor
public class ControleFestaController {

    private final ControleFestaService service;

    @GetMapping
    public ResponseEntity<List<ControleFestaResponseDTO>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ControleFestaResponseDTO> atualizar(@PathVariable Long id, @RequestBody ControleFestaRequestDTO dto) {
        return ResponseEntity.ok(service.updateControleFesta(id, dto));
    }

}
