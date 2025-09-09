package com.brothers.festas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_itens_cardapio")
@Getter @Setter @NoArgsConstructor
public class ItensCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCardapio;
    private String descricao;
}

