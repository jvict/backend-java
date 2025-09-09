package com.brothers.festas.controller;

import com.brothers.festas.dto.request.SobrasRequestDTO;
import com.brothers.festas.dto.response.SobrasResponseDTO;
import com.brothers.festas.service.SobrasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sobra")
@RequiredArgsConstructor
public class SobrasController {

    private final SobrasService service;

    @PostMapping
    public ResponseEntity<SobrasResponseDTO> salvar(@RequestBody SobrasRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SobrasResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<SobrasResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SobrasResponseDTO> atualizar(@PathVariable Long id, @RequestBody SobrasRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
