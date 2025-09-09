package com.brothers.festas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_itens_bar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItensBar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;
}
