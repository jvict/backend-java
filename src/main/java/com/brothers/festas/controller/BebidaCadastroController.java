package com.brothers.festas.controller;

import com.brothers.festas.dto.request.BebidaCadastroRequestDTO;
import com.brothers.festas.dto.response.BebidaResponseDTO;
import com.brothers.festas.service.BebidaCadastroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bebida")
@RequiredArgsConstructor
public class BebidaCadastroController {

    private final BebidaCadastroService bebidaService;

    @PostMapping
    public ResponseEntity<BebidaResponseDTO> salvar(@RequestBody BebidaCadastroRequestDTO dto) {
        return ResponseEntity.ok(bebidaService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<BebidaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(bebidaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BebidaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bebidaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BebidaResponseDTO> atualizar(@PathVariable Long id,
                                                       @RequestBody BebidaCadastroRequestDTO dto) {
        return ResponseEntity.ok(bebidaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        bebidaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
