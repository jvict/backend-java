package com.brothers.festas.dto.request;

import lombok.Data;

@Data
public class BebidaCadastroRequestDTO {
    private Long idBebida;
    private String descricao;
    private double litrosChopp;
    private boolean consignado;
}
