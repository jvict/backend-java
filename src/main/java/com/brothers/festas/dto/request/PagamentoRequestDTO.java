package com.brothers.festas.dto.request;

import com.brothers.festas.model.enums.EnumMeioPagamento;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PagamentoRequestDTO {

    private Long id;
    private Double valor;
    private EnumMeioPagamento meioPagamento;
    private LocalDate dataPagamento;
    private Boolean recebido;
    private String observacoes;
}
