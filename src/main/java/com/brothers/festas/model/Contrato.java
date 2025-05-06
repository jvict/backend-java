package com.brothers.festas.model;

import com.brothers.festas.model.enums.EnumTipoDoContrato;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_contrato")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "valor_recebido")
    private Double valorRecebido;

    @Column(name = "valor_pendente")
    private Double valorPendente;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipos_de_contrato")
    private EnumTipoDoContrato tipoDoContrato;

    @Column(name = "data_hora_inicial")
    private LocalDateTime dataHoraInicial;

    @Column(name = "data_hora_final")
    private LocalDateTime dataHoraFinal;

    private Integer duracao;

    @Column(name = "qtd_convidados")
    private String quantidadeConvidados;

    private String observacoes;
    private Double desconto;
    private Double acrescimo;

    @ManyToMany
    @JoinTable(
            name = "contrato_tema",
            joinColumns = @JoinColumn(name = "contrato_id"),
            inverseJoinColumns = @JoinColumn(name = "tema_id")
    )
    private List<Tema> temas;

    @ManyToMany
    @JoinTable(
            name = "contrato_item",
            joinColumns = @JoinColumn(name = "contrato_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemContrato> itensContrato = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "contrato_aniversariante",
            joinColumns = @JoinColumn(name = "contrato_id"),
            inverseJoinColumns = @JoinColumn(name = "aniversariante_id")
    )
    private List<Aniversariante> listaAniversariantes;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos;

}
