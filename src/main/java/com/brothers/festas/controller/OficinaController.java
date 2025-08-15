package com.brothers.festas.controller;

import com.brothers.festas.dto.request.OficinaRequestDTO;
import com.brothers.festas.dto.response.OficinaResponseDTO;
import com.brothers.festas.service.OficinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oficinas")
@RequiredArgsConstructor
public class OficinaController {

    private final OficinaService service;

    @PostMapping
    public ResponseEntity<OficinaResponseDTO> salvar(@RequestBody OficinaRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OficinaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<OficinaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OficinaResponseDTO> atualizar(@PathVariable Long id, @RequestBody OficinaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
