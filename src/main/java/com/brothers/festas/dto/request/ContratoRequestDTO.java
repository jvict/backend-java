package com.brothers.festas.dto.request;

import com.brothers.festas.model.Aniversariante;
import com.brothers.festas.model.enums.EnumSituacaoContrato;
import com.brothers.festas.model.enums.EnumTipoDoContrato;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContratoRequestDTO {

    private Long cliente;
    private Double valorRecebido;
    private Double valorPendente;
    private Double valorTotal;
    private EnumTipoDoContrato tipoDoContrato;
    private LocalDateTime dataHoraInicial;
    private LocalDateTime dataHoraFinal;
    private Integer duracao;
    private String quantidadeConvidados;
    private String observacoes;
    private Double desconto;
    private Double acrescimo;
    private EnumSituacaoContrato situacao;

    private List<AniversarianteRequestDTO> aniversariantes;

    private List<Long> itensContrato;
    private List<Long> temas;

}

