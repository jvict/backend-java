package com.brothers.festas.dto.response;

import com.brothers.festas.model.enums.EnumTipoDoContrato;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContratoResponseDTO {

    private Long id;
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

    private List<TemaResponseDTO> temas;
    private List<ItemContratoResponseDTO> itensContrato;
    private List<AniversarianteResponseDTO> listaAniversariantes;
    private List<PagamentoResponseDTO> pagamentos;
}

