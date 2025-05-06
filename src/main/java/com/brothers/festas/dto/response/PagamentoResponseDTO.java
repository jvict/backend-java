package com.brothers.festas.dto.response;

import com.brothers.festas.model.Pagamento;
import com.brothers.festas.model.enums.EnumMeioPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResponseDTO {

    private Long id;
    private Double valor;
    private EnumMeioPagamento meioPagamento;
    private LocalDate dataPagamento;
    private Boolean recebido;
    private String observacoes;
    private Long contratoId;

    public PagamentoResponseDTO(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.valor = pagamento.getValor();
        this.meioPagamento = pagamento.getMeioPagamento();
        this.dataPagamento = pagamento.getDataPagamento();
        this.recebido = pagamento.getRecebido();
        this.observacoes = pagamento.getObservacoes();
        this.contratoId = pagamento.getId();
    }
}
