package com.brothers.festas.dto.response;

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
    private String situacao;//Precisa entender o que seria
    private LocalDateTime dataHoraInicial;
    private LocalDateTime dataHoraFinal;
    private Double valorRecebido;
    private Double valorPendente;
    private Double valorTotal;

}

