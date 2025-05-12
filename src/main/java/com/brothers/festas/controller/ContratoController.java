package com.brothers.festas.controller;

import com.brothers.festas.dto.request.ClienteRequestDTO;
import com.brothers.festas.dto.request.ContratoRequestDTO;
import com.brothers.festas.dto.response.ClienteResponseDTO;
import com.brothers.festas.dto.response.ContratoCalendarioResponseDTO;
import com.brothers.festas.dto.response.ContratoResponseDTO;
import com.brothers.festas.exception.ServiceException;
import com.brothers.festas.model.Contrato;
import com.brothers.festas.model.enums.EnumSituacaoContrato;
import com.brothers.festas.util.ContratoMapper;
import com.brothers.festas.service.IContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http:localhost:3000/")
@RestController
@RequestMapping("/contrato")
@RequiredArgsConstructor
public class ContratoController {

    private final IContratoService contratoService;
    private final ContratoMapper contratoMapper;

    @GetMapping("/calendario")
    public Page<ContratoCalendarioResponseDTO> listarContratosCalendario(Pageable pageable) {
        return contratoService.listarContratosCalendario(pageable);
    }

    @GetMapping("/{id}")
    public ContratoResponseDTO buscarContratoPorId(@PathVariable Long id) {
        return contratoService.buscarContratoPorId(id);
    }

    @GetMapping
    public ResponseEntity<Page<ContratoResponseDTO>> findAll(
            Pageable pageable,
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "dataHoraInicial", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraInicial,
            @RequestParam(name = "dataHoraFinal", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataHoraFinal
    ) {
        Page<ContratoResponseDTO> page = contratoService.findAllByFilters(pageable, nome, dataHoraInicial, dataHoraFinal);
        return ResponseEntity.ok(page);
    }


    @PostMapping()
    public ResponseEntity<ContratoResponseDTO> criarContratoNovo(@RequestBody ContratoRequestDTO contratoRequestDTO) {

        return ResponseEntity.ok(contratoService.salvarContrato(contratoRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoResponseDTO> update(@RequestBody ContratoRequestDTO contratoRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(contratoService.update(id, contratoRequestDTO));
    }

    @PutMapping("/{id}/situacao/{situacao}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable EnumSituacaoContrato situacao) {
        try {
            return ResponseEntity.ok().body(contratoService.updateStatus(id, situacao));
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Situacao inválida. Use 'CANCELADO' ou 'EM_ANDAMENTO'.");
        }
    }
}
