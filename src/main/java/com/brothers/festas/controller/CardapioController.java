package com.brothers.festas.controller;

import com.brothers.festas.dto.request.CardapioRequestDTO;
import com.brothers.festas.dto.response.CardapioResponseDTO;
import com.brothers.festas.service.CardapioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
@RequiredArgsConstructor
public class CardapioController {

    private final CardapioService service;

    @PostMapping
    public ResponseEntity<CardapioResponseDTO> salvar(@RequestBody CardapioRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<CardapioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> atualizar(@PathVariable Long id, @RequestBody CardapioRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
