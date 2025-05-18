package com.brothers.festas.controller;

import com.brothers.festas.dto.request.PagamentoRequestDTO;
import com.brothers.festas.dto.response.PagamentoResponseDTO;
import com.brothers.festas.service.IPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    @Autowired
    private IPagamentoService service;

    @PostMapping("/{idContrato}")
    public ResponseEntity<List<PagamentoResponseDTO>> criar(@PathVariable Long idContrato, @RequestBody List<PagamentoRequestDTO> request) {
        return ResponseEntity.ok(service.criarPagamento(idContrato, request));
    }
}