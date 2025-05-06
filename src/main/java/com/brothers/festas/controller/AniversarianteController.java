package com.brothers.festas.controller;

import com.brothers.festas.dto.request.AniversarianteRequestDTO;
import com.brothers.festas.dto.response.AniversarianteResponseDTO;
import com.brothers.festas.dto.response.ItemContratoResponseDTO;
import com.brothers.festas.service.AniversarianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aniversariante")
public class AniversarianteController {
    @Autowired
    private AniversarianteService service;

    @PostMapping
    public ResponseEntity<AniversarianteResponseDTO> criar(@Valid @RequestBody AniversarianteRequestDTO request) {
        return ResponseEntity.ok(service.criarAniversariante(request));
    }
}
