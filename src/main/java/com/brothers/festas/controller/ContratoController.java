package com.brothers.festas.controller;

import com.brothers.festas.dto.request.ContratoRequestDTO;
import com.brothers.festas.dto.response.ContratoCalendarioResponseDTO;
import com.brothers.festas.dto.response.ContratoResponseDTO;
import com.brothers.festas.model.Contrato;
import com.brothers.festas.util.ContratoMapper;
import com.brothers.festas.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/contrato")
@RequiredArgsConstructor
public class ContratoController {

    private final ContratoService contratoService;
    private final ContratoMapper contratoMapper;

    @GetMapping
    public Page<ContratoResponseDTO> listarContratos(Pageable pageable) {
        return contratoService.listarContratos(pageable);
    }

    @GetMapping("/calendario")
    public Page<ContratoCalendarioResponseDTO> listarContratosCalendario(Pageable pageable) {
        return contratoService.listarContratosCalendario(pageable);
    }

    @GetMapping("/{id}")
    public ContratoResponseDTO buscarContratoPorId(@PathVariable Long id) {
        return contratoService.buscarContratoPorId(id);
    }

    @PostMapping()
    public ResponseEntity<ContratoResponseDTO> criarContratoNovo(@RequestBody ContratoRequestDTO contratoRequestDTO) {
        Contrato contrato = contratoMapper.toEntity(contratoRequestDTO);
        Contrato contratoSalvo = contratoService.salvarContrato(contrato);
        ContratoResponseDTO response = contratoMapper.toResponse(contratoSalvo);
        return ResponseEntity.ok(response);
    }
}
