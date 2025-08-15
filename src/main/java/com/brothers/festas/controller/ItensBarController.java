package com.brothers.festas.controller;

import com.brothers.festas.dto.request.ItensBarRequestDTO;
import com.brothers.festas.dto.response.ItensBarResponseDTO;
import com.brothers.festas.service.ItensBarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-bar")
@RequiredArgsConstructor
public class ItensBarController {

    private final ItensBarService service;

    @PostMapping
    public ResponseEntity<ItensBarResponseDTO> salvar(@RequestBody ItensBarRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensBarResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ItensBarResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItensBarResponseDTO> atualizar(@PathVariable Long id, @RequestBody ItensBarRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
