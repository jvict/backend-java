package com.brothers.festas.dto.request.controle;

import com.brothers.festas.dto.request.BebidaCadastroRequestDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ControleFestaRequestDTO {
    private LocalDate data;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
    private GeralRequestDTO geral;
    private Long oficinaId;
    private CardapioControleRequestDTO cardapio;
    private BebidaCadastroRequestDTO bebida;
    private DecoracaoRequestDTO decoracao;
    private ExtraRequestDTO extra;
    private Long contratoId;
}
