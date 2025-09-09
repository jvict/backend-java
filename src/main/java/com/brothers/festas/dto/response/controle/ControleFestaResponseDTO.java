package com.brothers.festas.dto.response.controle;

import com.brothers.festas.dto.response.BebidaResponseDTO;
import com.brothers.festas.dto.response.OficinaResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ControleFestaResponseDTO {

    private Long id;
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    private GeralResponseDTO geral;
    private OficinaResponseDTO oficina;
    private CardapioControleResponseDTO cardapio;
    private BebidaResponseDTO bebida;
    private DecoracaoResponseDTO decoracao;
    private ExtraResponseDTO extra;
    private Long idContrato;
}

