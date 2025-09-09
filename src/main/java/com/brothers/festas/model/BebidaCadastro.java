package com.brothers.festas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_bebida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BebidaCadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBebida;

    private String descricao;
}
