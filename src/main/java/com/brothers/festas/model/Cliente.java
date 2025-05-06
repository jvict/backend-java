package com.brothers.festas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cliente")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "celular")
    private String celular;

    @Column(name = "email")
    private String email;

    @Column(name = "documento", unique = true)
    private String documento;

    @Column(name = "cep")
    private String cep;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    // Dados de aniversariante relacionados ao contrato
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "idade_no_evento")
    private Integer idadeNoEvento;

    @Column(name = "tema")
    private String tema;

    // Contrato
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;
}
