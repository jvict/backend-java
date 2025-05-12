package com.brothers.festas.controller;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.service.ITemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http:localhost:3000/")
@RestController
@RequestMapping("/tema")
public class TemaController {
    @Autowired
    private ITemaService ITemaService;

    @PostMapping
    public ResponseEntity<TemaResponseDTO> criar(@RequestBody TemaRequestDTO request) {
        return ResponseEntity.ok(ITemaService.criarTema(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ITemaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TemaResponseDTO>> findAllByFilters(Pageable pageable,
                                                         @RequestParam(name = "descricao", required = false) String descricao) {
        return ResponseEntity.ok().body(ITemaService.findAllByFilters(pageable, descricao));
    }
}