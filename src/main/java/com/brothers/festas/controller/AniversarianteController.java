package com.brothers.festas.controller;

import com.brothers.festas.dto.request.AniversarianteRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;
import com.brothers.festas.service.AniversarianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/aniversariante")
public class AniversarianteController {
    @Autowired
    private AniversarianteService serviceAniversariante;

    @PostMapping
    public ResponseEntity<AniversarianteResponseDTO> criar(@Valid @RequestBody AniversarianteRequestDTO request) {
        return ResponseEntity.ok(serviceAniversariante.criarAniversariante(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AniversarianteResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceAniversariante.findById(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<AniversarianteResponseDTO>> findByName(Pageable pageable, @PathVariable String nome) {
        return ResponseEntity.ok().body(serviceAniversariante.findByNome(pageable, nome));
    }

    @GetMapping
    public ResponseEntity<Page<AniversarianteResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(serviceAniversariante.findAll(pageable));
    }
}
