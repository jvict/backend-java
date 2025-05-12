package com.brothers.festas.dto.response;

import com.brothers.festas.model.enums.EnumSituacaoContrato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContratoCalendarioResponseDTO {

    private Long id;
    private String nomeCliente;
    private LocalDateTime dataHoraInicial;
    private LocalDateTime dataHoraFinal;
    private Double valorRecebido;
    private Double valorPendente;
    private Double valorTotal;
    private EnumSituacaoContrato situacao;

}

