package com.brothers.festas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_aniversariante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aniversariante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    private Integer idade;

    @Column(name = "idade_evento")
    private Integer idadeNoEvento;

    @ManyToMany(mappedBy = "listaAniversariantes")
    private List<Contrato> contratos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tema_id")
    private Tema tema;
}
