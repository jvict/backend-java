package com.brothers.festas.controller;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/tema")
public class TemaController {
    @Autowired
    private TemaService temaService;

    @PostMapping
    public ResponseEntity<TemaResponseDTO> criar(@RequestBody TemaRequestDTO request) {
        return ResponseEntity.ok(temaService.criarTema(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(temaService.findById(id));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<Page<TemaResponseDTO>> findByName(Pageable pageable, @PathVariable String descricao) {
        return ResponseEntity.ok().body(temaService.findByDescricao(pageable, descricao));
    }

    @GetMapping
    public ResponseEntity<Page<TemaResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(temaService.findAll(pageable));
    }
}