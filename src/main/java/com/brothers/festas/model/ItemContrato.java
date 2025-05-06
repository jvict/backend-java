package com.brothers.festas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_item_contrato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Double valor;

    @ManyToMany(mappedBy = "itensContrato")
    private List<Contrato> contratos = new ArrayList<>();
}
