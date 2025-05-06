package com.brothers.festas.controller;

import com.brothers.festas.dto.request.TemaRequestDTO;
import com.brothers.festas.dto.response.TemaResponseDTO;
import com.brothers.festas.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tema")
public class TemaController {
    @Autowired
    private TemaService service;

    @PostMapping
    public ResponseEntity<TemaResponseDTO> criar(@RequestBody TemaRequestDTO request) {
        return ResponseEntity.ok(service.criarTema(request));
    }
}