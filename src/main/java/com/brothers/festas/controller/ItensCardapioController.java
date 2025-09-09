package com.brothers.festas.controller;

import com.brothers.festas.dto.request.ItensCardapioRequestDTO;
import com.brothers.festas.dto.response.ItensCardapioResponseDTO;
import com.brothers.festas.service.ItensCardapioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-cardapio")
@RequiredArgsConstructor
public class ItensCardapioController {

    private final ItensCardapioService service;

    @GetMapping
    public ResponseEntity<List<ItensCardapioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<ItensCardapioResponseDTO> criar(@RequestBody ItensCardapioRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

