package com.brothers.festas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "oficinas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Oficina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;
}
