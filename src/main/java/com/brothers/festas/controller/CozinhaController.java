package com.brothers.festas.controller;

import com.brothers.festas.dto.request.CozinhaRequestDTO;
import com.brothers.festas.dto.response.CozinhaResponseDTO;
import com.brothers.festas.service.CozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {

    private final CozinhaService service;

    @PostMapping
    public ResponseEntity<CozinhaResponseDTO> criar(@RequestBody CozinhaRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CozinhaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CozinhaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CozinhaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CozinhaRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
