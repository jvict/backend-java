package com.brothers.festas.controller;

import com.brothers.festas.dto.request.PagamentoRequestDTO;
import com.brothers.festas.dto.response.PagamentoResponseDTO;
import com.brothers.festas.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoService service;

    @PostMapping("/{idContrato}")
    public ResponseEntity<PagamentoResponseDTO> criar(@PathVariable Long idContrato, @RequestBody PagamentoRequestDTO request) {
        return ResponseEntity.ok(service.criarPagamento(idContrato, request));
    }
}