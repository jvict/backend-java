package com.brothers.festas.controller;

import com.brothers.festas.dto.request.AniversarianteRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;
import com.brothers.festas.service.IAniversarianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aniversariante")
public class AniversarianteController {
    @Autowired
    private IAniversarianteService serviceAniversariante;

    @PostMapping
    public ResponseEntity<AniversarianteResponseDTO> criar(@Valid @RequestBody AniversarianteRequestDTO request) {
        return ResponseEntity.ok(serviceAniversariante.criarAniversariante(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AniversarianteResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceAniversariante.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AniversarianteResponseDTO>> findAll(Pageable pageable,
                                                                   @RequestParam(name = "nome", required = false) String nome) {
        return ResponseEntity.ok().body(serviceAniversariante.findAllByFilters(pageable, nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AniversarianteResponseDTO> update(@RequestBody AniversarianteRequestDTO request, @PathVariable Long id) {
        return ResponseEntity.ok().body(serviceAniversariante.update(id, request));
    }
}
